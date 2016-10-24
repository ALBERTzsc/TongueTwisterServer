package team.abc.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 * @author yangyongdong
 * @since 2016-10-23
 *
 */
public class DateTranslator {

	/**
	 * 转换格式
	 */
	private static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

	/**
	 * 将毫秒值转换成格式化的日期
	 * 
	 * @param time
	 * @return 格式化日期
	 */
	public static String translateToFormatDate(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN);
		String Date = sdf.format(new Date(time));
		return Date;
	}

	/**
	 * 将格式化日期转换成毫秒值
	 * 
	 * @param formatDate
	 * @return 毫秒值
	 */
	public static Long translateToTimeMilli(String formatDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PATTERN);

		long millionSeconds = 0; // 毫秒
		try {
			millionSeconds = sdf.parse(formatDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return millionSeconds;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(translateToFormatDate(System.currentTimeMillis()));
		System.out.println(translateToFormatDate(1477212203500l));
		System.out.println(translateToTimeMilli("2016-10-23 16:43:23 500"));
	}

}
