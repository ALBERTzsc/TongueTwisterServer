package team.abc.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import team.abc.bean.IP;
import team.abc.dao.ConnManage;
import team.abc.dao.DAOFactory;
import team.abc.dao.IIpDAO;
import team.abc.servlet.APKDownload;
import team.abc.tools.ILocationRequest;
import team.abc.tools.LocationRequest;

public class LocationJob implements Job {

	private static final Logger LOG = Logger.getLogger(APKDownload.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		LOG.info("开始为新增IP更新定位信息……");
		IIpDAO ipDAO = DAOFactory.getIpDAOInstance();
		try {
		
			List<IP> ipList = ipDAO.queryAllIpWithEmptyLocation();
			ILocationRequest request = new LocationRequest();
			String location = null;
			for(IP ip : ipList){
				
				location = request.addressResult(ip.getUserIp());
				ip.setLocation(location);
				LOG.info(ip.getUserIp()+">>>>>>>>"+ip.getLocation());
			}
			
			ipDAO.updateIPLocation(ipList);

			ConnManage.commit();
			LOG.info("更新定位完毕……数量："+ipList.size());
		} catch (Exception e) {
			e.printStackTrace();
			ConnManage.roolback();
		} finally {
			ConnManage.closeConnection();
		}

	}
	
}
