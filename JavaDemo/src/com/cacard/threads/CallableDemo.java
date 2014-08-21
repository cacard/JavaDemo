/**
 * 		Callable
 * 
 * 		1.5+
 * 		泛型，可返回值
 * 		可throw checked exception
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

		// 实现Callable<T>
		class MyCallable implements Callable<Integer> {
			private int initValue = 0;

			public MyCallable(int initValue) { // ctor
				this.initValue = initValue;
			}

			@Override
			public Integer call() throws Exception { // 实现call()函数
				Thread.sleep(2000);
				return ++this.initValue;
			}
		}

		// #1:在主线程直接调用
		try {
			int r = new MyCallable(1).call();
			System.out.println("->" + r);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// #2:Callable提交给Executor，得到一个表示异步计算结果的Future。
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
