package com.cacard.javalang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatTest {

	static SimpleDateFormat df = null;

	public static void main(String[] args) {
		Date d = new Date();
		df = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss", Locale.US);
		System.out.println(df.format(d));

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.currentThread().join(1000);
						//Thread.sleep(7);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						Date d = new Date();
						System.out.println(df.format(d));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
