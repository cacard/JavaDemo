package com.cacard.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskʾ��
 * 
 * FutrueTask����һ��Future���ɿص��첽������̡����������һ��Task��ִ���壩��
 * 
 * ʹ�ó����� #1 submit���̳߳���
 * 
 */
public class FutureTaskDemo {
	public static void main(String[] args) {
		demo1();
	}

	private static void demo1() {
		ExecutorService executor = Executors.newCachedThreadPool();

		// ִ����
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() {
				try {
					Thread.currentThread().sleep(2000);
				} catch (Exception e) {
					// pass
				}
				return 100;
			}
		};

		// futureTask submit���̳߳�
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		executor.submit(futureTask);

		// block ��ǰ�߳��ý��
		try {
			int c = futureTask.get();
			System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
