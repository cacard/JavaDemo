package com.cacard.javademo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
	
	private static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args)
	{
		testOneResourceOnThread();
	}
	
	/**
	 *一个线程、一个锁（资源）会出现死锁吗？
	 *No,锁可以被一个线程多次Hold住
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
