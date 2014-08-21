/**
 * 		Callable
 * 
 * 		1.5+
 * 		���ͣ��ɷ���ֵ
 * 		��throw checked exception
 */

package com.cacard.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	public static void main(String[] args) {

		// ʵ��Callable<T>
		class MyCallable implements Callable<Integer> {
			private int initValue = 0;

			public MyCallable(int initValue) { // ctor
				this.initValue = initValue;
			}

			@Override
			public Integer call() throws Exception { // ʵ��call()����
				Thread.sleep(2000);
				return ++this.initValue;
			}
		}

		// #1:�����߳�ֱ�ӵ���
		try {
			int r = new MyCallable(1).call();
			System.out.println("->" + r);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// #2:Callable�ύ��Executor���õ�һ����ʾ�첽��������Future��
		ExecutorService executor = Executors.newSingleThreadExecutor();executor.e
		Future<Integer> f = executor.submit(new MyCallable(1));
		try {
			int r = f.get();
			System.out.println("->" + r);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
