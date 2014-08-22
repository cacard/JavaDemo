/**
 * 
 * 		Executors.DefaultThreadFactory
 * 
 */
package com.cacard.threads;

import java.util.concurrent.Executors;

public class ExecutorFactory {

	public static void main(String[] args) {

		// #1 使用 new Thread() 实现的线程
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

			}
		});

		// #2 使用Executors中的DefaultThreadFactory创建线程
		Thread t2 = Executors.defaultThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {

			}
		});

		
	}

}
