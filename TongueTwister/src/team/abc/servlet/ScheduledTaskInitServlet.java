package team.abc.servlet;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import team.abc.job.LocationJob;

/**
 * 定时任务的激发servlet，用以启动定时服务
 * 
 * @author zsc
 * @since 2016-10-30
 */
public class ScheduledTaskInitServlet extends HttpServlet {

	
	private static final int START_HOUR_POINT = 3;
	
	
	public ScheduledTaskInitServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {

		startLocationTask();

	}

	/**
	 * 负责启动一个定位任务
	 * @since 2016-10-30
	 */
	private void startLocationTask() {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			scheduler = schedulerFactory.getScheduler();
			JobDetail job = JobBuilder.newJob(LocationJob.class)//定位ip的任务
					.withIdentity("myJob", "group1") // name "myJob", group
														// "group1"
					.build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("myTrigger", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInHours(24)//每天启动一次。
									.repeatForever())
					.startAt(getDateNextDay(START_HOUR_POINT))//在第二天的三点钟启动任务。
					.build();
			
			/*Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("myTrigger", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInHours(10).repeatForever())
					.startNow()
					.build();*/

			scheduler.scheduleJob(job, trigger);

			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 返回第二天某个时间的日期
	 * @since 2016-10-30
	 * @param hour
	 * @return 第二天的某个时间的日期
	 */
	private Date getDateNextDay(int hour){
		
		GregorianCalendar  cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		System.out.println(">>>>>>>>>>>"+cal.getTime());
		
		return cal.getTime();
		
	}

}
