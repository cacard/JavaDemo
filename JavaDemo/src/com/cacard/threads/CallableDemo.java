package com.cacard.threads;

/**
 * Callable
 * 
 * - CallableΪʲô����֣�
 * - ����Щʹ�ó�����
 * 
 * 1.5
 * ���Runnable�����˸�����ֵ
 * 
 */
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

	/**
	 * �Զ���һ�� Callable
	 */
	private static final class MyCallable implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			Thread.sleep(2000); // ģ������ʱ
			return 100;
		}
	}

	public static void main(String[] args) {
		demo1();
	}

	/**
	 * Demo 1 Callable�ύ��Executor���õ�һ����ʾ�첽��������Future��
	 */
	private static void demo1() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Integer> f = executor.submit(new MyCallable());
		try {
			int r = f.get(); // will block current thread
			System.out.println("->" + r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
