/**
 * CountDownLatch
 * 
 * �����ǣ��ȴ�һ���߳����һ��������ٽ���ĳ�������
 * ���磬����̹߳�ͬ���㣬�ѽ���ŵ�һ���̰߳�ȫ�����ݽṹ���С��������̶߳�������Ϻ󣬵������㡱��latch.await�˳�������ִ������
 * ע�⣺latch�ĵȴ��������õ�await���ɲ�Ҫ��д��wait�������Object�ķ�������
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
 * ������
 * 
 */
class Dispatcher implements Runnable {

	private final int count = 5;
	private CountDownLatch latch = new CountDownLatch(count);

	@Override
	public void run() {

		// �Ż�:һ�㲻Ҫ�����¿�����̣߳�����ʹ���̳߳��Ż���
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
