
/**
 * ExecutorCompletionService
 * ���������������������������ڲ�ԭ�������LinkedBlockingQueue
 */

package com.cacard.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class ExecutorCompletionServiceDemo {

	public static void main(String[] args)
	{
		testEmptyBlocingQueue();
	}
	
	/**
	 * �����������Ϊ��ʱ��ȡ����������������BlockingQueue��������
	 */
	static void testEmptyBlocingQueue()
	{
		Executor exe = Executors.newFixedThreadPool(5);
		ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService<>(exe);
		
		try {
			System.out.println("the start.");
			ecs.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("the end.");
	}
	
}
