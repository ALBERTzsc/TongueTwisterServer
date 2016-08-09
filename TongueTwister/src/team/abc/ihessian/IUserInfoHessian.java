package team.abc.ihessian;

import java.util.List;

import team.abc.bean.UserInfo;

public interface IUserInfoHessian {
	
	/**
	 * 插入或更新一个用户信息
	 * @param userInfo
	 */
	void insertOrUpdateUser(UserInfo userInfo);
	
	/**
	 * 以闯关数为排名依据，得到指定数目的用户信息。
	 * @param num
	 * @return 用户信息列表
	 */
	List<UserInfo> getUsersOrderByRanking(int num);
	
	/**
	 * 
	 * @param userInfo
	 * @return 改用户的闯关排名。
	 */
	int getPassNum(UserInfo userInfo);
}
