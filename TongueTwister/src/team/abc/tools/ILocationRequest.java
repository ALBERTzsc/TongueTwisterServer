package team.abc.tools;
/**
 * 
 * @author Mr.Green
 *
 */
public interface ILocationRequest {
	/**
	 * 将ip转换成地址
	 * @param ip
	 * @return ip所在的地区
	 */
	String addressResult(String ip);
}