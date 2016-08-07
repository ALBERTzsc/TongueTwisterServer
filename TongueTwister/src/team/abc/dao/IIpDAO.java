package team.abc.dao;

import team.abc.bean.IP;


public interface IIpDAO {

	void insert(IP ip) throws Exception;

	boolean update(IP ip) throws Exception;

	long queryByIp(String userIp) throws Exception;

}
