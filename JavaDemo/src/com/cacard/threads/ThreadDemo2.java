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
		
		int n=0; // ��Ȼ�ǡ��߳̿ɹ����ģ���ʵ�޸ĵ��Ƕ���t�ı�����
		
		@Override
		public void run(){
			
			for(int i=0;i<=10000;i++){ // �̷߳ǰ�ȫ
				n++;
			}
			System.out.println(n+","+Thread.currentThread().getId());
			
		}
	}
	
}
