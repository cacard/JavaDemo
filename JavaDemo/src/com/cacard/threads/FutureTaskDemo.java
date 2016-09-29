package com.cacard.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
		demo2();
	}

	private static void demo1() {
		ExecutorService executor = Executors.newCachedThreadPool();

		// ִ����
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
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

	/**
	 * future��cancel���� ʹ��interrupt�ж��߳�
	 */
	private static void demo2() {
		ExecutorService es = Executors.newCachedThreadPool();

		Future<?> f = es.submit(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println("running...");
					// try {
					// Thread.sleep(500);
					// } catch (Exception e) {
					// e.printStackTrace();
					// }
				}
				System.out.println("interrupted!");
			}
		});

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		f.cancel(true);
		System.out.println("canceled");

	}
}
