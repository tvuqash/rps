package com.tvu.shqa.rpspage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeClass {
	public static String getDateTime(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
	}
	
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public static String getTime() {
		return new SimpleDateFormat("HH-mm-ss").format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getDateTime("yyyy-MM-dd-HH-mm-ss"));
		System.out.println(getDate());
		System.out.println(getTime());
	}

}
