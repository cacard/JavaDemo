/**
 *  	在synchronized方法中使用 wait()/notify()
 * 			- 调用的时候，采用this.wait()/this.notify()，锁定的是“this”。
 * 			- 多个线程必须共享对象。在synchronized block中，多个线程共享某个object。
 */

package com.cacard.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class WaitNotifyUsingSynchronizedMethod {

	AtomicInteger count = new AtomicInteger(0);
	
	public synchronized void methodWaiting() throws InterruptedException{ // using sync method,not sync block.means lock on 'this'.
		if(count.get()<10){
			System.out.println("-> before wait()");
			this.wait(); // 等待其它线程发送notify
			// ...得到通知后，继续执行，至于count是否真的>=10，取决于发送通知方是否在>=10时发送notify。
			// 为了保险，可再次判断
			// if(count.get()>=10){}
			System.out.println("-> continue after wait()"); // 虽然这句在 if(count<10)里面，其实已经>=10了。
		}
	}

	public synchronized void methodNotify() throws InterruptedException{
		while(count.get()<10){
			count.getAndIncrement();
			Thread.sleep(500);
			System.out.println("increment");
		}
		this.notify();
	}
	
	public static void main(String[] args){
		final WaitNotifyUsingSynchronizedMethod demo = new WaitNotifyUsingSynchronizedMethod();
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					demo.methodWaiting();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					demo.methodNotify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
