/**
 * wait()/notify()/notifyAll() 简单Demo
 * 
 * 特性
 * 1 wait()/notify()的操作对象是各个线程共用到的synchronized(object)，是object的操作。
 * 2 wait()时，释放锁。被notify后尝试获取锁。（如果是notifyAll多个线程，那么它们之间也是线程同步的）
 * 
 */

package com.cacard.threads;

public class WaitNotifyDemo {

	private static Object obj=new Object();
	
	public static void main(String[] args){
		
		// 线程1在obj上进行wait()
		new Thread(new Runnable(){
			@Override
			public void run() {
				synchronized(obj){
					try {
						System.out.println("thread"+Thread.currentThread().getId()+" before wait()");
						obj.wait();
						
						System.out.println("thread"+Thread.currentThread().getId()+" after wait()");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}}).start();
		
		
		// 线程2在obj上进行wait()
				new Thread(new Runnable(){
					@Override
					public void run() {
						synchronized(obj){
							try {
								System.out.println("thread"+Thread.currentThread().getId()+" before wait()");
								obj.wait();
								System.out.println("thread"+Thread.currentThread().getId()+" after wait()");
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}}).start();
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 主线程在obj上进行notify()/notifyAll()
		synchronized(obj){
			//obj.notify();
			obj.notifyAll();
		}
				
	}

}
