/**
 * test thread interrupt
 */

package com.cacard.javademo;

public class ThreadInterruptDemo {

	public static void main(String[] args)
	{
		Thread t = new Thread(new T());
		
		t.start();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		t.interrupt();
		System.out.println(t.isInterrupted());
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(t.isInterrupted());
	}
	
}

class T implements Runnable
{

	@Override
	public void run() {
		
		int i=999999999;
		while(i>0)
		{
			i--;
		}

		while(Thread.currentThread().isInterrupted()==false)
		{
			// block 10 s
			try {
				Thread.currentThread().sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		
		System.out.println("out");
		
	}
	
}
