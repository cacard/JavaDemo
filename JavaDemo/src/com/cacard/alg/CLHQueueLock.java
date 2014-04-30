/**
 * CLH Lock��һ���Ŷӵġ��޼����ġ���ƽ������������AQS����ʹ�ã���ǿ�棩��
 * ������ʵ����Java�µ�CLH Queue Lock��
 * ���Paper: http://www.cs.tau.ac.il/~shanir/nir-pubs-web/Papers/CLH.pdf
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
		
		// ��֤�߳��������Ⱥ�˳��
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
	

	// CLH Lock �ĺ���˼·
	// ÿ���̻߳�ȡ��ʱ����һ��Node������Queue��Node������������ǰ��״̬���ϸ�����Ƿ��ͷ��������͵�ǰ״̬���Ƿ���Ҫ������
	// ���ǰ�����һֱû���ͷ���������ǰ��״̬��������������ͱ�ʾ�ɻ������
	static final class Node
	{
		private Node pre; // (����Ҫ)
		private boolean state; // �Ƿ���Ҫ��
		
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
	
	private AtomicReference<Node> tail = new AtomicReference<>();//����β�������߳����ʱʹ�á����̹߳���
	private ThreadLocal<Node> myNode;//��ǰ�̵߳�Node��ע�����̱߳��ر���
	
	// ���캯���г�ʼ���̱߳��ر���
	public CLHQueueLock()
	{
		myNode = new ThreadLocal<Node>(){
			@Override
			protected Node initialValue()
			{
				return new Node(null/*�Ǳ���*/,true);
			}
		};
	}
	
	// ����
	void lock()
	{
		// ԭ�Ӳ�������ȡ��ǰ����ǰ����㣨��tail��������tail��ָ��ǰ��㣩
		Node preNode = tail.getAndSet(myNode.get());
		
		if(preNode==null){
			return; // û��ǰ����ֱ�Ӽ���ִ��
		}
		while(preNode.state){
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("while preNode:"+preNode.toString()); // �ȴ��Ľ���뼴��Ҫ�ͷŵĽ��Ӧ������ͬ��hashcode
		} //���ǰ��һֱ��Ҫ�����������ȴ�
	}
	
	// �ͷ���
	void unlock()
	{
		myNode.get().setState(false);
		System.out.println("unlock:myNode:"+myNode.get().toString());
	}
}
