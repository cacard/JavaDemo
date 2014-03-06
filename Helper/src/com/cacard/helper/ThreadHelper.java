package com.cacard.helper;

public class ThreadHelper {

	public static void TrySleep(long m)
	{
		try {
			Thread.currentThread().sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
