package tiquartet.CommonModule.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 进行字符串的转换.
 * @author greatlyr
 *
 */
public class StringUtility {
	//默认日期字符串样式.
	public static String DateFormat = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 按照标准格式转换为字符串.
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		return simpleDateFormat.format(date);
	}
	
	
	/**
	 * 按照标准格式转换为字符串.
	 * @param localDate
	 * @return
	 */
	public static String dateToString(LocalDate localDate){
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
	
	/**
	 * 返回该用户类型的中文名.
	 * @param type
	 * @return
	 */
	public static String valueOf(UserType type){
		switch(type){
			case 客户 :
				return "客户";
			case 酒店工作人员 :
				return "酒店工作人员";
			case 网站营销人员 :
				return "网站营销人员";
			case 网站管理员 :
				return "网站管理人员";
			default :
				return null;
		}
	}
	
	/**
	 * 返回该策略类型的中文名.
	 * @param type
	 * @return
	 */
	public static String strategyName(StrategyType type,int hotelID){
		switch (type) {
			case BIRTHDAY :
				return "生日特惠";
			case CIRCLE :
				return "会员特定商圈专属特惠";
			case COMPANY :
				return "合作企业客户特惠";
			case ROOMNUM :
				return "特定日期入住特惠";
			case TIME :
				if(hotelID==0) return "特定日期范围预订折扣";
				else return "特定日期范围住宿折扣";
			default :
				return "折扣策略";
			
		}
	}
	
	public static String strategyName(StrategyType type){
		return strategyName(type,1);
	}
}
