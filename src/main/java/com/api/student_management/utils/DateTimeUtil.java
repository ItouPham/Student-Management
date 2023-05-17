package com.api.student_management.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	
	public static ZonedDateTime format(String date) {
		String result = date.replace(" ", "T").concat("+07:00");
		return ZonedDateTime.parse(result);
	}
	
	public static String formatRespones(ZonedDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		String formattedString = date.format(formatter);
		return formattedString;
	}
}
