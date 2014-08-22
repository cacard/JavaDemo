/**
 * 
 * 		Executors.DefaultThreadFactory
 * 
 */
package com.cacard.threads;

import java.util.concurrent.Executors;

public class ExecutorFactory {

	public static void main(String[] args) {

		// #1 ʹ�� new Thread() ʵ�ֵ��߳�
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {

			}
		});

		// #2 ʹ��Executors�е�DefaultThreadFactory�����߳�
		Thread t2 = Executors.defaultThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {

			}
		});

		
	}

}
