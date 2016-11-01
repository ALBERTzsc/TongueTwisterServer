package team.abc.dao;

import java.util.List;

import team.abc.bean.IP;


public interface IIpDAO {

	void insert(IP ip) throws Exception;

	boolean update(IP ip) throws Exception;

	long queryByIp(String userIp) throws Exception;
	
	List<IP> queryAllIpWithEmptyLocation() throws Exception;

	void updateIPLocation(List<IP> ipList) throws Exception;
}
