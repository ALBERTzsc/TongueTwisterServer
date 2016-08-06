package team.abc.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnManage {
	private static ThreadLocal currentConn = new ThreadLocal();

	/*
	 * 获得数据库连接,将数据库连接存入线程
	 */
	public static Connection currentConnection() throws SQLException {

		Connection conn = (Connection) currentConn.get();

		if (conn == null) {
			//System.out.println("get新连接");
			conn = new DataBaseConnection().getConnection();
			currentConn.set(conn);
			openTransaction();
		}
		//System.out.println("get旧连接");
		return conn;

	}

	/*
	 * 操作前初始化
	 */
	public static void openTransaction() {
		try {
			Connection conn = currentConnection();

			conn.setAutoCommit(false); // 关闭自动提交
			conn.setTransactionIsolation(2); // 防止脏读
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 释放数据库连接
	 */
	public static void closeConnection() {
		try {
			Connection conn = (Connection) currentConn.get();
			currentConn.set(null);
			//System.out.println("close走到这了");
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 执行
	 */
	public static void commit() {
		try {
			Connection conn = currentConnection();
			if (conn != null)
				conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 数据回滚
	 */
	public static void roolback() {
		try {
			// System.out.println("回滚");
			Connection conn = currentConnection();
			if (conn != null)
				conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
