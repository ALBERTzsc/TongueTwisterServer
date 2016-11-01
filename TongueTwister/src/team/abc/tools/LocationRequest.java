package team.abc.tools;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @param urlAll
 *            :请求接口
 * @param httpArg
 *            :参数
 * @return 返回结果
 */

public class LocationRequest implements ILocationRequest {
	private static final String HTTP_URL = "http://apis.baidu.com/showapi_open_bus/ip/ip";
	private static final String MY_API_KEY = "0f98fde43e358f5e27ba08a58a88f26d";

	private String request(String httpUrl, String httpArg) {
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		result = extracted(httpUrl, result, sbf);
		return result;
	}

	/**
	 * 获取 httuUrl, result ,sbf
	 * 
	 * @author Mr.Green 2016.10.28
	 * @param httpUrl
	 * @return
	 */
	private String extracted(String httpUrl, String result, StringBuffer sbf) {
		BufferedReader reader;
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", MY_API_KEY);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取 httpArg
	 * 
	 * @param IP
	 * @return httpArg
	 */
	public String getHttpArg(String IP) {
		return "ip=" + IP;
	}

	/**
	 * 通过输入查询获取的对象，转换成我们所需要的“**国**省**市**村” 如果是局域网，则输出“局域网”
	 * 
	 * @param object
	 * @return
	 */
	private String JsonAnalysis(String object) {
		String result = "";
		JsonParser parser = new JsonParser();
		JsonObject obj = (JsonObject) parser.parse(object);
		JsonObject obj2 = obj.get("showapi_res_body").getAsJsonObject();

		JsonObject subObject = obj2.getAsJsonObject();
		try {
			if ("局域网".equals(subObject.get("country").getAsString()))
				result = subObject.get("country").getAsString();
			else {
				result += subObject.get("country").getAsString() + subObject.get("region").getAsString()
						+ subObject.get("city").getAsString() + subObject.get("county").getAsString();
			}
		} catch (NullPointerException e) {
			if (subObject.get("country").getAsString() != null)
				result += subObject.get("country").getAsString();
			else
				result = "Can't search!";
		}
		return result;
	}

	@Override
	public String addressResult(String ip) {
		String httpArg = getHttpArg(ip);
		String jsonResult = request(HTTP_URL, httpArg);
		// System.out.println(jsonResult);
		return JsonAnalysis(jsonResult);
	}

}
