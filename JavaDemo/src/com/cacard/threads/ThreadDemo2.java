package com.cacard.threads;

public class ThreadDemo2 {
	
	public static void main(String[] args){
		MyThread t = new MyThread();
		
		Thread t1 = new Thread(t,"t1");
		Thread t2 = new Thread(t,"t2");
		
		t1.start();
		t2.start();
	}

	public static class MyThread extends Thread{
		
		int n=0; // 竟然是“线程可共享”的？其实修改的是对象t的变量，
		
		@Override
		public void run(){
			
			for(int i=0;i<=10000;i++){ // 线程非安全
				n++;
			}
			System.out.println(n+","+Thread.currentThread().getId());
			
		}
	}
	
}
