package com.cacard.javademo;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReadWriteLockDemo {

	public static void main(String[] args)
	{
		Demo2();
	}
	
	static void Demo1()
	{
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				lock.readLock().lock();
				System.out.println("thread1 get readlock.");
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				lock.readLock().unlock();
				
			}});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("thread2 try get write lock ...");
				lock.writeLock().lock();
				System.out.println("thread2 get writelock.");
				lock.writeLock().unlock();
			}});
		
		t1.start();
		t2.start();
		
		try
		{
			t1.join();
			t2.join();
		}
		catch(Exception e){}
		
		System.out.println(lock.getReadLockCount());
		
		
	
	}
	
	/**
	 * WriteLock降级为读锁
	 * 1 得到WL
	 * 2 同时得到RL
	 * 3 释放WL
	 * ->降级为RL
	 */
	static void Demo2()
	{
		final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				lock.writeLock().lock(); // get write lock
				lock.readLock().lock(); // get read lock
				lock.writeLock().unlock();// release the write lock
				//do something ...
				lock.readLock().unlock();
				System.out.println("ok.");
				
			}}).start();
	}
	
}
