/**
 * Barrier���Ľ���(Zhang)դ(Zha)�����߳�Ϊ����
 * CPUָ��������ָ���Ҫ�����ڶ��CPU֮�������ͬ����
 * �ڴ�����ָ���Ƕ���߳�֮�������ͬ����
 * ����ο� MemoryBarrier/Barrier
 * 
 * Java�µ�CyclicBarrier��˼�ǡ�������/�ɸ������ϡ�
 */

package com.cacard.javademo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierDemo {

	public static void main(String[] args) {
		anotherDemo();
	}

	private void simpleDemo() {
		final CyclicBarrier barrier = new CyclicBarrier(5);

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println("thread"
							+ Thread.currentThread().getId());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ...
					try {
						System.out.println("await,ready count = "
								+ barrier.await()); // �����ȴ������߳��������
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}

				}
			}).start();
		}

		System.out.println("�������,ready count is " + barrier.getNumberWaiting());
	}

	static volatile int loop = 1;

	/**
	 * CyclicBarrier(N,Runnable)
	 */
	private static void anotherDemo() {
		final int threadCount = 5;
		final int loopCount = 3;
		final CyclicBarrier barrier = new CyclicBarrier(threadCount,
				new Runnable() {

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
						System.out.println("thread"
								+ Thread.currentThread().getId()
								+ " running.already waiting count="
								+ barrier.getNumberWaiting());
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
