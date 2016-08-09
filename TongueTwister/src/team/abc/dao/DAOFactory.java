package team.abc.dao;

import java.sql.SQLException;

public class DAOFactory {

	public static IIpDAO getIpDAOInstance(){
		
		//数据库有唯一的连接
		try {
			return new IpDAOImpl(ConnManage.currentConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static IUserInfoDAO getUserInfoDAOInstance(){
		try {
			return new UserInfoDAOImpl(ConnManage.currentConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
