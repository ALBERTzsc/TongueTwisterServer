package team.abc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

	private final String DBDRIVER = "com.mysql.jdbc.Driver";

	//private final String DBURL = "jdbc:mysql://127.0.0.1:3306/userdata?characterEncoding=utf8&useSSL=false";
	private final String DBURL = "jdbc:mysql://182.61.51.97:3306/tonguetwister?characterEncoding=utf8&useSSL=false";

	private final String DBUSER = "abcteam";

	private final String DBPASSWORD = "abcteam@2016";

	private Connection conn = null;

	public DataBaseConnection() {

		try {

			Class.forName(DBDRIVER);

			this.conn =

			DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);

		} catch (Exception e) {

			System.out.println("加载数据库驱动失败！");

		}

	}

	public Connection getConnection() {

		System.out.println("开始连接数据库！");
		return conn;

	}

	public static void main(String[] args) {
		DataBaseConnection dbc = new DataBaseConnection();
		Connection conn = dbc.getConnection();
	}
}