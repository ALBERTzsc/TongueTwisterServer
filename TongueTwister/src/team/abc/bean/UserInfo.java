package team.abc.bean;

import java.io.Serializable;

/**
 * @author zsc
 *
 */
public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private int userGender;
	private int challengePassNum;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserGender() {
		return userGender;
	}

	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}

	public int getChallengePassNum() {
		return challengePassNum;
	}

	public void setChallengePassNum(int challengePassNum) {
		this.challengePassNum = challengePassNum;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName
				+ ", userGender=" + userGender + ", challengePassNum="
				+ challengePassNum + "]";
	}
	
	
}
