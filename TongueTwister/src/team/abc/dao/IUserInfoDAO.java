package team.abc.dao;

import java.util.List;

import team.abc.bean.UserInfo;

public interface IUserInfoDAO {
	
	/**
	 * 插入一条用户信息
	 * @param userInfo
	 */
	void insertUserInfo(UserInfo userInfo);
	
	/**
	 * 更新一条用户信息
	 * @param userInfo
	 */
	void updateUserInfo(UserInfo userInfo);
	
	/**
	 * 查询符合条件的用户信息
	 * @param id
	 * @return 符合条件的数量
	 */
	boolean queryUserInfoById(long id);
	
	/**
	 * 按挑战数目查询前num个用户信息
	 * @param num
	 * @return 用户信息列表
	 */
	List<UserInfo> queryUserInfosOrderByRanking(int num);
	
	/**
	 * 通过id查询某个用户挑战关数排名
	 * @param id
	 * @return 该用户的关数排名
	 */
	int queryChallengePassNumById(long id);
}
