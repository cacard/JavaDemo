package com.cacard.javademo;


public class ThreadDemo {

	public static void main(String[] args)
	{
		testWait();
	}
	
	static void testState()
	{
		Thread t = new Thread(new MyThread());
		
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				String state = Thread.currentThread().getState().toString();
				System.out.println(state);
				
			}});
	}
	
	static void testWait()
	{
		
		final X x = new X();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("->t1");
				x.N();
				
			}}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("->t2");
				x.N();// 此处，t2被阻塞等待了5秒钟。被阻塞时，t2也一直轮询t1是否释放锁。如何避免轮询呢？
				
			}}).start();
	}
	
}

class X
{
	public String name;
	
	public synchronized void N()
	{
		System.out.println("->N()");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("N()->");
	}
	
	public synchronized void M()
	{
		while(true)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

class MyThread implements Runnable
{

	public MyThread()
	{
		String state = Thread.currentThread().getState().toString();
		System.out.println(state);
	}
	
	@Override
	public void run() {

		while(true)
		{
			String state = Thread.currentThread().getState().toString();
			System.out.println(state);
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}