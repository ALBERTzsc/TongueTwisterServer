package team.abc.bean;

public class IP {

	private String userIp;
	private long time;
	
	public IP() {

	}

	public IP(String userIp , long time){
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
