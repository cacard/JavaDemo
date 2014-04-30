/**
 * CLH Lock：一种排队的、无饥饿的、公平的自旋锁，在AQS中有使用（增强版）。
 * 本代码实现了Java下的CLH Queue Lock。
 * 相关Paper: http://www.cs.tau.ac.il/~shanir/nir-pubs-web/Papers/CLH.pdf
 */

package com.cacard.alg;

import java.util.concurrent.atomic.AtomicReference;

public class CLHQueueLock {

	public static void main(String[] args)
	{
		testQueueLock();
	}
	
	static void testQueueLock()
	{
		final CLHQueueLock lock = new CLHQueueLock();

		
		new Thread(new Runnable(){
			@Override
			public void run() {
				lock.lock();
				System.out.println("enter thread1");
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				lock.unlock();
				System.out.println("exit thread1,lock.state="+lock.myNode.get().state);
			}}).start();
		
		// 保证线程启动的先后顺序
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("try enter thread2");
				lock.lock();
				System.out.println("enter thread2");
				lock.unlock();
				System.out.println("exit thread2");
			}}).start();
		
	}
	

	// CLH Lock 的核心思路
	// 每个线程获取锁时创建一个Node并进入Queue。Node包含了两个域：前驱状态（上个结点是否释放了锁）和当前状态（是否需要锁）。
	// 如果前驱结点一直没有释放锁，则在前驱状态域上自旋。否则就表示可获得锁。
	static final class Node
	{
		private Node pre; // (不需要)
		private boolean state; // 是否需要锁
		
		public Node(Node pre,boolean state)
		{
			this.pre=pre;
			this.state=state;
		}
		
		public void setState(boolean state)
		{
			this.state=state;
		}
	}
	
	private AtomicReference<Node> tail = new AtomicReference<>();//队列尾部，新线程入队时使用。（线程共享）
	private ThreadLocal<Node> myNode;//当前线程的Node，注意是线程本地变量
	
	// 构造函数中初始化线程本地变量
	public CLHQueueLock()
	{
		myNode = new ThreadLocal<Node>(){
			@Override
			protected Node initialValue()
			{
				return new Node(null/*非必须*/,true);
			}
		};
	}
	
	// 加锁
	void lock()
	{
		// 原子操作：获取当前结点的前驱结点（旧tail），更新tail（指向当前结点）
		Node preNode = tail.getAndSet(myNode.get());
		
		if(preNode==null){
			return; // 没有前驱，直接继续执行
		}
		while(preNode.state){
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("while preNode:"+preNode.toString()); // 等待的结点与即将要释放的结点应该有相同的hashcode
		} //如果前驱一直需要锁，则自旋等待
	}
	
	// 释放锁
	void unlock()
	{
		myNode.get().setState(false);
		System.out.println("unlock:myNode:"+myNode.get().toString());
	}
}
