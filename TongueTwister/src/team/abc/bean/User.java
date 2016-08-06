package team.abc.bean;

public class User {

	private String userIp;
	private long time;
	
	public User() {

	}

	public User(String userIp , long time){
		this.userIp = userIp;
		this.time = time;
	}
	
	
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
