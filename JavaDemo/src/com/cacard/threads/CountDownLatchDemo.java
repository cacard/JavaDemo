/**
 * CountDownLatch
 * 
 * 作用是，等待一组线程完成一组任务后，再进行某项操作。
 * 比如，多个线程共同计算，把结果放到一个线程安全的数据结构当中。等所有线程都处理完毕后，到达“集结点”，latch.await退出阻塞，执行任务。
 * 注意：latch的等待集结点调用的await，可不要错写成wait（这个是Object的方法）。
 * 
 */

package com.cacard.threads;

import java.lang.Thread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		new Thread(new Dispatcher()).start();
	}
}

/**
 * 调度器
 * 
 */
class Dispatcher implements Runnable {

	private final int count = 5;
	private CountDownLatch latch = new CountDownLatch(count);

	@Override
	public void run() {

		// 优化:一般不要这样新开多个线程，可以使用线程池优化。
		for (int i = 0; i < count; i++) {
			new Thread(new WorkThread(latch)).start();
		}

		try {
			System.out.println("[main thread] is awaiting ...");
			latch.await();
			System.out.println("[main thread] Latch count down over.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

class WorkThread implements Runnable {
	private CountDownLatch latch = null;

	public WorkThread(CountDownLatch l) {
		this.latch = l;
	}

	@Override
	public void run() {

		System.out.println("thread id(" + Thread.currentThread().getId() + ") do work");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		latch.countDown();

	}

}
