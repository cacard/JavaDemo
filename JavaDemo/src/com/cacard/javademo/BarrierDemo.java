/**
 * Barrier中文叫障(Zhang)栅(Zha)，或者称为屏障
 * CPU指令有屏障指令，主要是用于多个CPU之间的数据同步。
 * 内存屏障指令是多个线程之间的数据同步。
 * 更多参考 MemoryBarrier/Barrier
 * 
 * Java下的CyclicBarrier意思是“周期性屏障”
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
						System.out.println("await,ready count = "+barrier.await()); // 阻塞等待其它线程运行完毕
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					
				}}).start();
		}
		
		System.out.println("集结结束,ready count is "+barrier.getNumberWaiting());
	}
	
}
