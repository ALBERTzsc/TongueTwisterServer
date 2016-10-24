package team.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import team.abc.bean.IP;
import team.abc.tools.DateTranslator;

public class IpDAOImpl implements IIpDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private static final String IP_TABLE_NAME = "tb_ip_record";

	public IpDAOImpl(Connection conn) {
		this.conn = conn;
	}

	// 向数据库中插入数据
	public void insert(IP ip) throws Exception {

		String sql = "insert into "+ IP_TABLE_NAME +"(userIp,time) values(?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ip.getUserIp());
		pstmt.setString(2, DateTranslator.translateToFormatDate(ip.getTime()));
		pstmt.executeUpdate();
		pstmt.close();
		System.out.println("已插入数据");
	}

	// 向数据库中更新数据
	public boolean update(IP ip) throws Exception {
		String sql = "update "+IP_TABLE_NAME+" set time=? where userIp=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, DateTranslator.translateToFormatDate(ip.getTime()));
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
		String sql = "select userIp,time from "+IP_TABLE_NAME+" where ? in(userIp)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userIp);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("该ip已存在！！！");
			IP preUser = new IP();
			preUser.setTime(DateTranslator.translateToTimeMilli(rs.getString(2)));
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
