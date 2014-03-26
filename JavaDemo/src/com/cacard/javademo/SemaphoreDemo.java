/**
 * 信号量可以看做一把锁，这把锁可以被n个线程同时获取，n就是“量”。
 */

package com.cacard.javademo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {
	
	public static void main(String[] args)
	{
		final Semaphore s = new Semaphore(10);//10个线程可同时获取该锁
		final AtomicInteger atomic = new AtomicInteger(1);
		
		for(int i=0;i<50;i++)
		{
			new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						s.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread"+Thread.currentThread().getId()+"acquired.falg="+atomic.getAndIncrement());
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					s.release();
					
				}}).start();
		}
		
	}
	
}
