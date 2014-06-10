/**
 * wait()/notify()/notifyAll() ��Demo
 * 
 * ����
 * 1 wait()/notify()�Ĳ��������Ǹ����̹߳��õ���synchronized(object)����object�Ĳ�����
 * 2 wait()ʱ���ͷ�������notify���Ի�ȡ�����������notifyAll����̣߳���ô����֮��Ҳ���߳�ͬ���ģ�
 * 
 */

package com.cacard.threads;

public class WaitNotifyDemo {

	private static Object obj=new Object();
	
	public static void main(String[] args){
		
		// �߳�1��obj�Ͻ���wait()
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
		
		
		// �߳�2��obj�Ͻ���wait()
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
		
		// ���߳���obj�Ͻ���notify()/notifyAll()
		synchronized(obj){
			//obj.notify();
			obj.notifyAll();
		}
				
	}

}
