/*
 * Executor
 * 
 * ��һ��ִ�����ӿ�
 * �����ʵ�ֿ��Զ��ֶ�����
 * -> ����ִ����
 * -> ����ִ����
 * -> �̳߳�
 * -> ��ʱ��
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

// �ڵ��÷����߳���ִ��Runnable
// ����������ExecutorԴ���ĵ�
class SameThreadExecutor implements Executor {
	@Override
	public void execute(Runnable r) {
		r.run();
	}
}

// �ڵ��÷����߳��д��л�ִ�ж��Runnable
class SerialExecutorUsingInvoke implements Executor {
	private ConcurrentLinkedQueue<Runnable> list = new ConcurrentLinkedQueue<Runnable>();
	private Thread t = new Thread();

	@Override
	public void execute(Runnable command) {
		list.add(command); // ��β
		for (Runnable r = list.poll(); r != null;) {
			r.run();
		}
	}
}

// TODO:���¿������߳��д��л�ִ�ж��Runnable
// see android.os.AsyncTask
class SerialExecutorUsingNewThread implements Executor {

	@Override
	public void execute(Runnable arg0) {

	}

}