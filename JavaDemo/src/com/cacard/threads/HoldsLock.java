/**
 * 		���֪����ǰ�߳���ĳ���������Ƿ��Ѿ�������
 * 			- Thread.holdsLock(obj);
 * 
 */

package com.cacard.threads;

public class HoldsLock {

	private Object obj = new Object();

	public void method() {

		synchronized (obj) {
			System.out.println(Thread.holdsLock(obj));
		}

		{
			System.out.println(Thread.holdsLock(obj));
		}

	}

	public static void main(String[] args) {
		new HoldsLock().method();
	}

}
