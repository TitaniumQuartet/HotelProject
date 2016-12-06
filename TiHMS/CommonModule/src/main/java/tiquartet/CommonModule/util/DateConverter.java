package tiquartet.CommonModule.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 用统一的日期格式进行字符串与日期实例间的转换.
 * @author greatlyr
 *
 */
public class DateConverter {
	//默认日期字符串样式.
	public static String DateFormat = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 按照标准格式转换为字符串.
	 * @param date
	 * @return
	 */
	public static String toString(Date date){
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 按照标准格式转换为字符串.
	 * @param localDate
	 * @return
	 */
	public static String toString(LocalDate localDate){
		return simpleDateFormat.format(localDate);
	}
	
	/**
	 * 将日期字符串转换为Calendar实例.
	 * @param dateString
	 * @return
	 */
	public static Calendar toCalendar(String dateString){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(simpleDateFormat.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
			return calendar;
		}
		return calendar;
	}
}
