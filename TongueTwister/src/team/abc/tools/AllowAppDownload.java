package team.abc.tools;

import team.abc.bean.IP;
import team.abc.dao.ConnManage;
import team.abc.dao.DAOFactory;
import team.abc.dao.IIpDAO;

public class AllowAppDownload {
	
	private static final int TEN_SECONDS = 10*1000;
	
	private IP ip;
	public AllowAppDownload(IP ip) {
		this.ip = ip;
	}
	/**
	 * 同一个ip在5分钟之内不能重复下载 
	 * 
	 * @param user
	 * @return 是否可以下载app
	 */
	public boolean allowDownload() {
		IIpDAO ipDAO = DAOFactory.getIpDAOInstance();
		try {
			if (ipDAO.queryByIp(ip.getUserIp()) == 0) {
				ipDAO.insert(ip);
				ConnManage.commit();
				return true;
			} else {
				if (ip.getTime() - ipDAO.queryByIp(ip.getUserIp()) > TEN_SECONDS) {
					ipDAO.update(ip);
					ConnManage.commit();
					return true;
				} else {
					ipDAO.update(ip);
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
			IP ip = new IP("192.168.1.5",System.currentTimeMillis());
			AllowAppDownload allowAppDownload = new AllowAppDownload(ip);
			System.out.println(allowAppDownload.allowDownload());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
