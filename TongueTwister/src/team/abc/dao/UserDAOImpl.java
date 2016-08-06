package team.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import team.abc.bean.User;

public class UserDAOImpl implements IUserDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserDAOImpl(Connection conn) {
		this.conn = conn;
	}

	// 向数据库中插入数据
	public void insert(User user) throws Exception {

		String sql = "insert into tb_ip values(?,?)";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserIp());
		pstmt.setLong(2, user.getTime());
		pstmt.executeUpdate();
		pstmt.close();
		System.out.println("已插入数据");
	}

	// 向数据库中更新数据
	public boolean update(User user) throws Exception {
		String sql = "update tb_ip set time=? where userIp=?";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, user.getTime());
		pstmt.setString(2, user.getUserIp());
		System.out.println("已更新数据" + pstmt.executeUpdate());
		if (pstmt.executeUpdate() > 0) {
			pstmt.close();
			return true;
		} else {
			pstmt.close();
			return false;
		}

	}

	// 向数据库中查找指定ip的时间戳
	public long queryByIp(String userIp) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select userIp,time from tb_ip where ? in(userIp)";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userIp);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("该ip已存在！！！");
			User preUser = new User();
			preUser.setTime(rs.getLong(2));
			rs.close();
			pstmt.close();
			return preUser.getTime();
		} else {
			rs.close();
			pstmt.close();
			return 0;
		}

	}

	public static void main(String[] args) {
		IUserDAO userDAO = DAOFactory.getUserDAOInstance();
		try {
			User user = new User();
			user.setUserIp("192.168.1.4");
			user.setTime(System.currentTimeMillis());
			userDAO.insert(user);
			userDAO.queryByIp(user.getUserIp());
			System.out.println("数据库中查询userIp相应的返回值为："
					+ userDAO.queryByIp(user.getUserIp()));
			System.out.println(userDAO.update(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
