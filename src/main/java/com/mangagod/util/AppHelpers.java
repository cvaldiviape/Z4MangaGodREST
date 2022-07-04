package com.mangagod.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppHelpers {

	public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		// Get current date time
		LocalDateTime currentDateTime = localDateTime;
		// Inbuilt format
		// DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		// Custom format if needed
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// Format LocalDateTime
		String formattedDateTime = currentDateTime.format(formatter);
		
		return formattedDateTime;
	}
}