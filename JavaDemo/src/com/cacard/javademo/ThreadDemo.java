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
	
	private int sum=0;
	public static int s=0;
	
	/**
	 * 测试 wait/notify，即条件变量
	 */
	static void testWait()
	{
		
		final X x = new X();
		
		// for sum++
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<60;i++)
					x.Add();
				
			}});
		
		
		// for sum++
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<60;i++)
					x.Add();
				
			}});
		
		// for condition:when sum>=100,stop and print
		Thread t3 = new Thread(new Runnable(){

			@Override
			public void run() {
				x.Clear();
				
			}});
		
		t3.start();
		t1.start();
		t2.start();
		

	}
	
}

class X
{
	public String name;
	
	// for thread1/thread2 add 
	public synchronized void Add()
	{
		if(ThreadDemo.s>=100)
		{
			System.out.println("Add(),Condition OK.");
			this.notify();
		}
		else
		{
			System.out.println("Add(),Condition not OK,++.");
			ThreadDemo.s++;
		}
	}
	
	// for condition
	public synchronized void Clear()
	{
		while(!(ThreadDemo.s>=100))
		{
			System.out.println("Clear(),Condition not OK.wait.");
			try {
				this.wait(); // Thread State is WAITING
				System.out.println(Thread.currentThread().getState());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Clear(),Condition OK. s="+ThreadDemo.s);
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