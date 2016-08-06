package team.abc.dao;

import java.sql.SQLException;

public class DAOFactory {

	public static IUserDAO getUserDAOInstance(){
		
		//数据库有唯一的连接
		try {
			return new UserDAOImpl(ConnManage.currentConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
