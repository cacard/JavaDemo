package com.cacard.javalang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
	
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args)
	{
		Demo0();
	}
	
	/**
	 * �̳߳����쳣�����ͷ�����
	 */
	static void Demo0()
	{
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				lock.lock();
				
			}});
		
		t.start();
		
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println(lock.isLocked());
	}
	
	/**
	 *һ���̡߳�һ��������Դ�������������
	 *No,�����Ա�һ���̶߳��Holdס
	 */
	public static void testOneResourceOnThread()
	{
		lock.lock();
		System.out.println("lock 1st");
		lock.lock();
		System.out.println("lock 2st");
		System.out.println("isHeldByCurrentThread:"+lock.isHeldByCurrentThread());
		System.out.println("getHoldCount:"+lock.getHoldCount());
		lock.unlock();
	}
	
	static synchronized void M()
	{
		System.out.println("->M()");
		M();
	}
	
}

class XX
{
	synchronized void M()
	{
		System.out.println("->M()");


		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("thread ->"+Thread.currentThread().getId());
				XX.this.M();
				
			}}).start();;
		
	}
}
