package com.cacard.javalang;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		System.out.println(calendar.getTime());
	}
	
}
