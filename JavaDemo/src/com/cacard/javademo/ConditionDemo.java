/**
 * �������� Condition
 * Ӧ�ó������������ڲ�����Ҫ�ȴ���������ô�����ͷ����������ȴ��������������󣬻����������ִ��
 * ���������ο� object �� wait() / notify() ����
 */

package com.cacard.javademo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	public static int count = 0;// ��Ҫͬ��
	public static Lock lock = new ReentrantLock();//��
	public static Condition condition = lock.newCondition();//����Ӧ������

	public static void main(String[] args) {
		new Thread(new ConditionWaiter()).start();
		new Thread(new ConditionMaker()).start();
	}

}

// ����������
class ConditionMaker implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 11; i++) {
			ConditionDemo.lock.lock();
			ConditionDemo.count++;
			System.out.println("Maker ++ ,="+ConditionDemo.count);
			if (ConditionDemo.count >= 10) {
				System.out.println("Maker notify");
				ConditionDemo.condition.signalAll();//֪ͨ�����̣߳��������㡣�ͷŵ�ǰ��
				return;
			}
			ConditionDemo.lock.unlock();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

// �����ȴ���
class ConditionWaiter implements Runnable {

	@Override
	public void run() {
		System.out.println("->Waiter run()");
		ConditionDemo.lock.lock();// ��ȡ���󣬲ſɲ�����Condition
		while (ConditionDemo.count < 10) {
			try {
				System.out.println("->Waiter await.)");
				ConditionDemo.condition.await();// ���������㣬�ͷŵ�ǰ�����ȴ��źţ�һ����ȡ�źţ����»����������������ִ�У�
			
				System.out.println("->Waiter's condition ok.and count="+ConditionDemo.count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("->Waiter wait over.)");
		}
		ConditionDemo.lock.unlock();

	}

}