/*
 * Executor
 * 
 * 是一个执行器接口
 * 其具体实现可以多种多样：
 * -> 串行执行器
 * -> 并行执行器
 * -> 线程池
 * -> 定时器
 */

package com.cacard.threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class ExecutorDemo {

	public static void main(String[] args) {
		testSerialExecutorUsingInvoke();
	}

	static void testSerialExecutorUsingInvoke() {
		Executor e = new SerialExecutorUsingInvoke();
		for(int i=0;i<10;i++){
			e.execute(new Runnable(){
				@Override
				public void run() {
					System.out.println("runing @ "+Thread.currentThread().getId());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}});
		}
	}

}

// 在调用方的线程中执行Runnable
// 例子来自于Executor源码文档
class SameThreadExecutor implements Executor {
	@Override
	public void execute(Runnable r) {
		r.run();
	}
}

// 在调用方的线程中串行化执行多个Runnable
class SerialExecutorUsingInvoke implements Executor {
	private ConcurrentLinkedQueue<Runnable> list = new ConcurrentLinkedQueue<Runnable>();
	private Thread t = new Thread();

	@Override
	public void execute(Runnable command) {
		list.add(command); // 队尾
		for (Runnable r = list.poll(); r != null;) {
			r.run();
		}
	}
}

// TODO:在新开单独线程中串行化执行多个Runnable
// see android.os.AsyncTask
class SerialExecutorUsingNewThread implements Executor {

	@Override
	public void execute(Runnable arg0) {

	}

}