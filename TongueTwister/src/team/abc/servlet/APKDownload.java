package team.abc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.log4j.Logger;

import team.abc.bean.User;
import team.abc.tools.AllowAppDownload;

public class APKDownload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private static final String SHORT_TIME_URL = "HTML/shortTime.html";
	private static final String DOWNLOAD_URL = "Download/TongueTwister.apk";
	
	private static final Logger LOG = Logger.getLogger(APKDownload.class);
	public APKDownload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		
		User user = new User(ip,System.currentTimeMillis());
		
		AllowAppDownload check = new AllowAppDownload(user);
		
		if(check.allowDownload()){
			LOG.info(user.getUserIp()+"请求下载成功。");
			
			//服务器端跳转 网址不变， forward
			//request.getRequestDispatcher(DOWNLOAD_URL).forward(request,response);
			//客户端跳转 网址改变， redirect
			response.sendRedirect(DOWNLOAD_URL);
		}else{
			
			LOG.info(user.getUserIp()+"在规定时间间隔内重复请求。");

			request.getRequestDispatcher(SHORT_TIME_URL).forward(request,response);
			
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
