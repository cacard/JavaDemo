package com.cacard.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * FutureTask示例
 * 
 * FutrueTask既是一个Future（可控的异步计算过程、结果）又是一个Task（执行体）。
 * 
 * 使用场景： #1 submit到线程池中
 * 
 */
public class FutureTaskDemo {
	public static void main(String[] args) {
		demo2();
	}

	private static void demo1() {
		ExecutorService executor = Executors.newCachedThreadPool();

		// 执行体
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				return 100;
			}
		};

		// futureTask submit到线程池
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		executor.submit(futureTask);

		// block 当前线程拿结果
		try {
			int c = futureTask.get();
			System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * future的cancel操作 使用interrupt中断线程
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
