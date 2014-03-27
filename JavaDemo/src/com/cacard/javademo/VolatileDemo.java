/*
 * volatile�������̰߳�ȫ
 * ֻ��֤����volatile��һ��д�����������߳̿ɼ�����ÿ��д������������volatile������������Կ������̰߳�ȫ�ġ�
 */

package com.cacard.javademo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

	private static volatile int flag=0;
	private static AtomicInteger atomic = new AtomicInteger(0);
	
	public static void main(String[] args)
	{
		Demo1();
	}
	
	/**
	 * ���߳��£���volatile�����ĸ�ֵ������volatile����������val=val++���������̰߳�ȫ�Ĳ���
	 */
	static void Demo1()
	{
		final int threadCount=1000;
		final CyclicBarrier cb = new CyclicBarrier(threadCount,new Runnable(){

			@Override
			public void run() {
				
				System.out.println("last result\nflag:"+flag+"\natomic:"+atomic.get());
				
			}});
		
		for(int i=0;i<threadCount;i++)
		{
			final int m=i;
			new Thread(new Runnable(){

				@Override
				public void run() {
					
					flag++;
					atomic.getAndIncrement();
					
					
					try {
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					} 
					
				}}).start();
		}
	}
	
	/**
	 * ��volatile������д���Ҳ�������volatile�������������̰߳�ȫ�ģ�
	 */
	static void Demo2()
	{
		for(int i=0;i<10;i++)
		{
			final int m=i;
			new Thread(new Runnable(){

				@Override
				public void run() {
					
					flag=m; // ��volatile��������һ��д������ע�ⲻ���ȶ���д��flag=falg+1�������д�������̰߳�ȫ��
					System.out.println(flag); // �������������̵߳�д���Ե�ǰ�̵߳Ķ��ǿɼ��ġ�
					
				}}).start();
		}	
	}
	
}
