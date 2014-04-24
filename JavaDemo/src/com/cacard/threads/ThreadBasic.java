package com.cacard.threads;

public class ThreadBasic {

	public static void main(String[] args){
		
		System.out.println(Thread.currentThread().getId());
		
		// 调用run不是新开线程
		new BasicThread().run(); 
		
	}
	
}

class BasicThread implements Runnable
{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getId());
		
	}
	
}
