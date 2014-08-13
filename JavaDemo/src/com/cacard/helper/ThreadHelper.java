package com.cacard.helper;

public class ThreadHelper {

	public static void TrySleep(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
