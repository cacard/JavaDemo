package com.cacard.threads;

/**
 * Callable
 * 
 * - Callable为什么会出现？
 * - 有哪些使用场景？
 * 
 * 1.5
 * 相比Runnable，多了个返回值
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
	 * 自定义一个 Callable
	 */
	private static final class MyCallable implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			Thread.sleep(2000); // 模拟计算耗时
			return 100;
		}
	}

	public static void main(String[] args) {
		demo1();
	}

	/**
	 * Demo 1 Callable提交给Executor，得到一个表示异步计算结果的Future。
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
