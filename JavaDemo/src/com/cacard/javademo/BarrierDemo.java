/**
 * Barrier���Ľ���(Zhang)դ(Zha)�����߳�Ϊ����
 * CPUָ��������ָ���Ҫ�����ڶ��CPU֮�������ͬ����
 * �ڴ�����ָ���Ƕ���߳�֮�������ͬ����
 * ����ο� MemoryBarrier/Barrier
 * 
 * Java�µ�CyclicBarrier��˼�ǡ����������ϡ�
 */


package com.cacard.javademo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierDemo {

	public static void main(String[] args)
	{
		final CyclicBarrier barrier = new CyclicBarrier(5);
		
		for(int i=0;i<10;i++)
		{
			new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println("thread"+Thread.currentThread().getId());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// ...
					try {
						System.out.println("await,ready count = "+barrier.await()); // �����ȴ������߳��������
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					
				}}).start();
		}
		
		System.out.println("�������,ready count is "+barrier.getNumberWaiting());
	}
	
}
