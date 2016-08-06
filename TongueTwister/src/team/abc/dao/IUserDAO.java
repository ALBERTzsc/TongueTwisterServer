package team.abc.dao;

import team.abc.bean.User;


public interface IUserDAO {

	void insert(User user) throws Exception;

	boolean update(User user) throws Exception;

	long queryByIp(String userIp) throws Exception;

}
