
/**
 * ExecutorCompletionService
 * 用来解耦新增任务和已完成任务，内部原理采用了LinkedBlockingQueue
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
	 * 当已完成任务为空时，取操作会阻塞（来自BlockingQueue的阻塞）
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
