package team.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import team.abc.bean.IP;

public class IpDAOImpl implements IIpDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public IpDAOImpl(Connection conn) {
		this.conn = conn;
	}

	// 向数据库中插入数据
	public void insert(IP ip) throws Exception {

		String sql = "insert into tb_ip values(?,?)";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ip.getUserIp());
		pstmt.setLong(2, ip.getTime());
		pstmt.executeUpdate();
		pstmt.close();
		System.out.println("已插入数据");
	}

	// 向数据库中更新数据
	public boolean update(IP ip) throws Exception {
		String sql = "update tb_ip set time=? where userIp=?";
		//System.out.println(sql);
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, ip.getTime());
		pstmt.setString(2, ip.getUserIp());
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
			IP preUser = new IP();
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

}
