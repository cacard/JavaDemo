package com.cacard.threads;

public class ThreadDemo {

	public static void main(String[] args) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("end");
				
			}}).start();
		
		System.out.println("main over");
	}

	/**
	 * 线程状态
	 */
	static void testState() {

		// ---------------------------------------------
		// new Thread
		// Thread t1 = new Thread(); //state: NEW
		// t1.start(); //state: RUNNABLE
		//
		// try {
		// Thread.sleep(1);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(t1.getState());// state: TERMINATED or RUNNABLE

		// --------------------------------------------
		// runnable thread
		// Thread t2 = new Thread(new MyRunnableThread());
		// t2.setName("t2");
		// System.out.println(t2.getState());

		// sub thread
		MySubThread t3 = new MySubThread();
		t3.setName("t3");
		System.out.println(t3.getState());
		t3.start();

	}

	// -------------------------------
	// wait/notify
	private int sum = 0;
	public static int s = 0;

	/**
	 * 测试 wait/notify，即条件变量
	 */
	static void testWait() {

		final X x = new X();

		// for sum++
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 60; i++)
					x.Add();

			}
		});

		// for sum++
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 60; i++)
					x.Add();

			}
		});

		// for condition:when sum>=100,stop and print
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				x.Clear();

			}
		});

		t3.start();
		t1.start();
		t2.start();

	}

}

class MyRunnableThread implements Runnable {

	public MyRunnableThread() {
		String state = Thread.currentThread().getState().toString();
		System.out.println("[MyRunnableThread ctor]" + state + ",name:" + Thread.currentThread().getName());
	}

	public void displayState() {
		String state = Thread.currentThread().getState().toString();
		System.out.println("[MyRunnableThread displayState]" + state);
	}

	@Override
	public void run() {

		System.out.println("run");
		while (true) {
			String state = Thread.currentThread().getState().toString();
			System.out.println("in run:" + state + ",name=" + Thread.currentThread().getName());

			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

class MySubThread extends Thread {
	public MySubThread() {
		String state = Thread.currentThread().getState().toString();
		System.out.println("[MyRunnableThread ctor]" + state + ",name:" + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		System.out.println("run");
		while (true) {
			String state = Thread.currentThread().getState().toString();
			System.out.println("in run:" + state + ",name=" + Thread.currentThread().getName());

			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class X {
	public String name;

	// for thread1/thread2 add
	public synchronized void Add() {
		if (ThreadDemo.s >= 100) {
			System.out.println("Add(),Condition OK.");
			this.notify();
		} else {
			System.out.println("Add(),Condition not OK,++.");
			ThreadDemo.s++;
		}
	}

	// for condition
	public synchronized void Clear() {
		while (!(ThreadDemo.s >= 100)) {
			System.out.println("Clear(),Condition not OK.wait.");
			try {
				this.wait(); // Thread State is WAITING
				System.out.println(Thread.currentThread().getState());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Clear(),Condition OK. s=" + ThreadDemo.s);
	}

}
