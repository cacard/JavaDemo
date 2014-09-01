package com.cacard.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService_Demo {

	private static void test() {
		ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
		es.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("=>");

			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		test();
	}

}
