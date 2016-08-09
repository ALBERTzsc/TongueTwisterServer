package team.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.abc.bean.IP;
import team.abc.bean.UserInfo;

public class UserInfoDAOImpl implements IUserInfoDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	public UserInfoDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertUserInfo(UserInfo userInfo) {

		String sql = "insert into tb_user values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, userInfo.getUserId());
			pstmt.setString(2, userInfo.getUserName());
			pstmt.setInt(3, userInfo.getUserGender());
			pstmt.setInt(4, userInfo.getChallengePassNum());
			pstmt.executeUpdate();
			System.out.println("已插入数据:" + userInfo.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		String sql = "update tb_user set user_name=?,user_gender=?,challenge_pass_num=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userInfo.getUserName());
			pstmt.setInt(2, userInfo.getUserGender());
			pstmt.setInt(3, userInfo.getChallengePassNum());
			pstmt.setLong(4, userInfo.getUserId());
			pstmt.executeUpdate();
			System.out.println("已更新数据:" + userInfo.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public boolean queryUserInfoById(long id) {
		// TODO Auto-generated method stub

		String sql = "select * from tb_user where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("该id已存在");

				return true;
			} else {
				System.out.println("该id不存在");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<UserInfo> queryUserInfosOrderByRanking(int num) {
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		
		String sql = "select * from tb_user order by challenge_pass_num desc limit ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			ConnManage.commit();
			
			UserInfo userInfo = null;
			
			while(rs.next()){
				userInfo = new UserInfo();
				userInfo.setUserId(rs.getLong(1));
				userInfo.setUserName(rs.getString(2));
				userInfo.setUserGender(rs.getInt(3));
				userInfo.setChallengePassNum(rs.getInt(4));
				userInfoList.add(userInfo);
			}
			
		}catch (SQLException e) {
			ConnManage.roolback();
			e.printStackTrace();
			return null;
		} finally {
			try {
				pstmt.close();
				ConnManage.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userInfoList;
	}

	@Override
	public int queryChallengePassNumById(long id) {

		String sql = "select * from tb_user order by challenge_pass_num desc";
		
		try{
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ConnManage.commit();
			
			UserInfo userInfo = null;
			
			while(rs.next()){
				
				if(rs.getLong(1) == id){
					return rs.getRow();
				}
				
			}
			
			
			return -1;
			
		}catch (SQLException e) {
			ConnManage.roolback();
			e.printStackTrace();
			return -1;
		} finally {
			try {
				pstmt.close();
				ConnManage.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		UserInfo userInfo = new UserInfo();

		userInfo.setUserId(11111);
		userInfo.setUserName("182xxxx3129");
		userInfo.setUserGender(-1);
		userInfo.setChallengePassNum(21);

		IUserInfoDAO iuserInfoDAO = DAOFactory.getUserInfoDAOInstance();

		//iuserInfoDAO.insertUserInfo(userInfo);
		
		userInfo.setUserGender(1);
		
		//iuserInfoDAO.updateUserInfo(userInfo);
		

		//System.out.println(iuserInfoDAO.queryUserInfoById(19));

		//System.out.println(iuserInfoDAO.queryUserInfosOrderByRanking(3));
		
		System.out.println(iuserInfoDAO.queryChallengePassNumById(123123));
	}

}
