package team.abc.hessianimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import team.abc.bean.UserInfo;
import team.abc.dao.ConnManage;
import team.abc.dao.DAOFactory;
import team.abc.dao.IUserInfoDAO;
import team.abc.ihessian.IUserInfoHessian;
import team.abc.servlet.APKDownload;

public class UserInfoHessianImpl implements IUserInfoHessian{
	
	private static final Logger LOG = Logger.getLogger(UserInfoHessianImpl.class);
	
	@Override
	public void insertOrUpdateUser(UserInfo userInfo) {
		
		IUserInfoDAO iuserInfoDAO = DAOFactory.getUserInfoDAOInstance();
		
		if(iuserInfoDAO.queryUserInfoById(userInfo.getUserId())){
		
			LOG.info("更新用户："+userInfo);
			iuserInfoDAO.updateUserInfo(userInfo);
			ConnManage.commit();
			
		}else{
			
			LOG.info("插入用户："+userInfo);
			iuserInfoDAO.insertUserInfo(userInfo);
			ConnManage.commit();
			
		}
		
		ConnManage.closeConnection();
		
	}

	@Override
	public List<UserInfo> getUsersOrderByRanking(int num) {
		// TODO Auto-generated method stub
		LOG.info("正在获取获取排行榜……");
		IUserInfoDAO iuserInfoDAO = DAOFactory.getUserInfoDAOInstance();
		return iuserInfoDAO.queryUserInfosOrderByRanking(num);
		
	}

	@Override
	public int getPassNum(UserInfo userInfo) {
		LOG.info(userInfo.getUserId()+"正在获取排名……");
		IUserInfoDAO userInfoDAO = DAOFactory.getUserInfoDAOInstance();
		return userInfoDAO.queryChallengePassNumById(userInfo.getUserId());
	}

}
