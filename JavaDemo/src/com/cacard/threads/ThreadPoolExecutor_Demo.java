/**
 * ThreadPoolExecutor���̳߳أ�ԭ��̽��
 * 
 * 		- �ڲ������̻߳���AQS
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
	 * newFixedThreadPool(n) - corePoolSizeΪn - ���Ŷ�Ϊ���ޣ������ϣ�
	 */
	private static void testFixedPool() {
		ExecutorService pool = Executors.newFixedThreadPool(10); // ��С�̶�Ϊ10���̳߳�

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
	 * �����Զ��� ThreadPoolExecutor
	 * 		- corePoolSize �����������еĹ����߳�
	 * 		- maximumPoolSize �����߳������
	 * 		- BlockingQueue �ŶӶ���
	 * 		- RejectedExecutionHandler ���Ŷӱ��ܾ�ʱ�Ļص�
	 * 
	 * 		���ٿɴ��� BlockingQueue����+maximumPoolSize����
	 */
	private static void testThreadPoolExecutor() {
		BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(20); // �����ŶӶ���
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy(); // �׳��쳣
		RejectedExecutionHandler handler2 = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println("->���ܾ�");
			}
		};

		ThreadPoolExecutor pool = new ThreadPoolExecutor(1/* corePoolSize */, 5/* maximumPoolSize */, 0, TimeUnit.SECONDS, bq, handler2);

		// ���̳߳ط���100���߳�
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
