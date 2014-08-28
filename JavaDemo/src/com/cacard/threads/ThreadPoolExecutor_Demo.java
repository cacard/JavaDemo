/**
 * ThreadPoolExecutor（线程池）原理探索
 * 
 * 		- 内部工作线程基于AQS
 */

package com.cacard.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor_Demo {

	/**
	 * newFixedThreadPool(n) - corePoolSize为n - 可排队为无限（理论上）
	 */
	private static void testFixedPool() {
		ExecutorService pool = Executors.newFixedThreadPool(10); // 大小固定为10的线程池

		for (int i = 0; i < 100; i++) {
			final int j = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread" + j + " start running...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread" + j + " start finished.");

				}
			});
		}
	}

	/**
	 * 测试自定义 ThreadPoolExecutor
	 * 		- corePoolSize 池中最少运行的工作线程
	 * 		- maximumPoolSize 工作线程最大量
	 * 		- BlockingQueue 排队队列
	 * 		- RejectedExecutionHandler 当排队被拒绝时的回调
	 * 
	 * 		最少可处理 BlockingQueue数量+maximumPoolSize个。
	 */
	private static void testThreadPoolExecutor() {
		BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(20); // 任务排队队列
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy(); // 抛出异常
		RejectedExecutionHandler handler2 = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("->被拒绝");
			}
		};

		ThreadPoolExecutor pool = new ThreadPoolExecutor(1/* corePoolSize */, 5/* maximumPoolSize */, 0, TimeUnit.SECONDS, bq, handler2);

		// 向线程池放入100个线程
		for (int i = 0; i < 100; i++) {
			final int j = i;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(26000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread" + j + " finished.");

				}
			});
		}

		System.out.println("end");

	}

	public static void main(String[] args) {
		testThreadPoolExecutor();
	}

}
