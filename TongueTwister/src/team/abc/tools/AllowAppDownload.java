package team.abc.tools;

import team.abc.bean.User;
import team.abc.dao.ConnManage;
import team.abc.dao.DAOFactory;
import team.abc.dao.IUserDAO;

public class AllowAppDownload {
	
	private static final int TEN_SECONDS = 10*1000;
	
	private User user;
	public AllowAppDownload(User user) {
		this.user = user;
	}
	/**
	 * 同一个ip在5分钟之内不能重复下载 
	 * 
	 * @param user
	 * @return 是否可以下载app
	 */
	public boolean allowDownload() {
		IUserDAO userDAO = DAOFactory.getUserDAOInstance();
		try {
			if (userDAO.queryByIp(user.getUserIp()) == 0) {
				userDAO.insert(user);
				ConnManage.commit();
				return true;
			} else {
				if (user.getTime() - userDAO.queryByIp(user.getUserIp()) > TEN_SECONDS) {
					userDAO.update(user);
					ConnManage.commit();
					return true;
				} else {
					userDAO.update(user);
					ConnManage.commit();
					return false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ConnManage.roolback();
		}finally{
			ConnManage.closeConnection();
		}
		return false;

	}

	public static void main(String[] args) {

		try {
			User user = new User("192.168.1.5",System.currentTimeMillis());
			AllowAppDownload allowAppDownload = new AllowAppDownload(user);
			System.out.println(allowAppDownload.allowDownload());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
