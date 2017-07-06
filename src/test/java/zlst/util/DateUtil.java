package zlst.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//根据指定格式返回指定时间
	public static String formatDt(Date date,String format){
		String result ="";
		try{
			if(date != null){
				SimpleDateFormat df = new SimpleDateFormat(format);
				result = df.format(date);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//根据指定格式返回当前时间
	public static String formatDt(String format){
		String result ="";
		try{
			SimpleDateFormat df = new SimpleDateFormat(format);
			result = df.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//返回年份
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	//返回月份
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH)+1;
	}
	
	//返回第几天
	public static int getDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	//返回小时
	public static int getHour(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	//返回分钟
	public static int getMinute(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}
	
	//返回秒
	public static int getSecond(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

}
