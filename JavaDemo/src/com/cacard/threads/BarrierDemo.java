/**
 * Barrier中文叫障(Zhang)栅(Zha)，或者称为屏障
 * CPU指令有屏障指令，主要是用于多个CPU之间的数据同步。
 * 内存屏障指令是多个线程之间的数据同步。
 * 更多参考 MemoryBarrier/Barrier
 * 
 * Java下的CyclicBarrier意思是“周期性/可复用屏障”
 * 
 * 应用场景：多个任务在任意一个都完成的情况下执行另外一个任务。
 */

package com.cacard.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierDemo {

	public static void main(String[] args) {
		simpleDemo();
	}

	private static void simpleDemo() {
		final CyclicBarrier barrier = new CyclicBarrier(5/* 参与的线程数量 */);

		for (int i = 0; i < 5/* 若线程<CyclicBarrier集结数量，则永不会到达集结点 */; i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("thread" + j + " start running...");

					// 休眠一段时间
					try {
						Thread.sleep(1000 + j * 300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					try {
						barrier.await(); // 通知CyclicBarrier当前任务已经完成，并阻塞当前线程，直到CyclicBarrier接收到某个数量的完成任务。
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}

					System.out.println("thread" + j + " end await,continue...");

				}
			}).start();
		}

		System.out.println("集结结束,ready count is " + barrier.getNumberWaiting());
	}

	static volatile int loop = 1;

	/**
	 * CyclicBarrier(N,Runnable)
	 */
	private static void anotherDemo() {
		final int threadCount = 5;
		final int loopCount = 3;
		final CyclicBarrier barrier = new CyclicBarrier(threadCount, new Runnable() {

			@Override
			public void run() {
				System.out.println("barrier is over and loop=" + loop);
				loop++;
			}
		});

		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		for (int j = 0; j < loopCount; j++) {
			for (int i = 0; i < threadCount; i++) {
				pool.execute(new Runnable() {

					@Override
					public void run() {
						System.out.println("thread" + Thread.currentThread().getId() + " running.already waiting count=" + barrier.getNumberWaiting());
						try {
							barrier.await();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
		pool.shutdown();

	}

}
