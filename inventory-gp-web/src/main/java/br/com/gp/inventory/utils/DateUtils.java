package br.com.gp.inventory.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils extends br.com.embracon.j4e.util.DateUtils {

	public static Date sunDays(Date date, int amountDays) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amountDays);
		return calendar.getTime();
	}
	
	public static Date subtractDays(Date date, int amountDays) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amountDays);
		return calendar.getTime();
	}
		
}
