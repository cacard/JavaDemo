/**
 * Atomic ԭ�Ӳ�������
 * ����CAS
 * ABA
 */

package com.cacard.javademo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Atomic {

	public static void main(String[] args)
	{
		testAtomicMarkableReference();
	}
	
	/**
	 * AtomicInteger
	 */
	static void testInt()
	{
		// ���ڶ��߳��¹�������ĵ�������ʵ��ͬ�����̰߳�ȫ��
		final AtomicInteger atomForIncrease = new AtomicInteger(0);
		int threadCount=100;
		Thread[] threads= new Thread[threadCount];
		for(int i=0;i<threadCount;i++)
		{
			threads[i]=new Thread(new Runnable(){

				@Override
				public void run() {
					for(int j=0;j<1000;j++)
						atomForIncrease.incrementAndGet();
				}});
			threads[i].start();
		}
		for(Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("->Result:"+atomForIncrease.get());
		
		// ���ڱȽϸ��£���ÿ���޸ĵ�������1���ǵ������ݼ���
		AtomicInteger atomForCAS = new AtomicInteger(0);
		// read
		int rOld = atomForCAS.get();
		// edit
		int rNew = rOld+2;
		// CAS
		boolean isOK = atomForCAS.compareAndSet(rOld, rNew);
		if(isOK)
		{
			System.out.println("->CAS success,new value is "+atomForCAS.get());
		}
		else
		{
			System.out.println("->CAS fail,new value is "+atomForCAS.get());
		}
		
		
		// ABA Problem
		AtomicInteger atomicForABA = new AtomicInteger(0);
		// read
		int old = atomicForABA.get();
		// �޸Ĳ�д��
		atomicForABA.getAndIncrement();	// =1
		// �޸ĳɳ�ʼֵ
		atomicForABA.getAndDecrement(); // =0
		
		// CAS �ɹ�����ʵ��ʱ��0�Ѿ��Ǳ�ʱ��0
		boolean ok = atomicForABA.compareAndSet(old, 2);
		if(ok)
		{
			System.out.println("->CAS success,new value is "+atomForCAS.get());
		}
		else
		{
			System.out.println("->CAS fail,new value is "+atomForCAS.get());
		}
		
		
	}
	
	/**
	 * AtomicIntegerArray
	 */
	static void testAtomicIntegerArray()
	{
		AtomicIntegerArray array = new AtomicIntegerArray(new int[]{0,0,0});
		array.getAndIncrement(0);
		System.out.println(array.get(0));
	}
	
	/**
	 * TODO FieldUpdater
	 */
	static void testFieldUpdater()
	{
		// AtomicIntegerFieldUpdater
		
		
		// AtomicReferenceFieldUpdater
		
	}
	
	/**
	 * AtomicReference
	 */
	static void testRef()
	{
		// AtoicReference
		Data old = new Data(1,1);
		AtomicReference<Data> atomRef = new AtomicReference<Data>(old); 
		Data old1 = atomRef.get();
		System.out.println(old==old1);	// true,˵��ͨ�� get �õ��Ķ����ָ����Դ������ͬ
		
		old.a=100;	// �޸� old������ڲ��ֶΣ�CAS�ɹ�����Ϊ�����ָ��δ��
		//old=new Data(1000,2000); // oldָ���¶���CASʧ��
		// �������CAS
		boolean b = atomRef.compareAndSet(old,  new Data(2,3));
		
		if(b)
		{
			System.out.println("CAS success.");
		}
		else
		{
			System.out.println("CAS fail.");
		}	
	}
	
	/**
	 * AtomicMarkableReference
	 * ����һ��bool������ǵĶ���CAS
	 * һ��bool��ǿ��Է�ֹABA��
	 */
	static void testAtomicMarkableReference()
	{
		Data old = new Data(0,0);
		AtomicMarkableReference<Data> markRef = new AtomicMarkableReference<Data>(old,true);
		
		boolean oldFlag = markRef.isMarked(); // �ɶ���ĸ������λ
		
		Data newObj = new Data(1,1);	// �¶���
		boolean b = markRef.compareAndSet(old/*�ɶ���*/, newObj, oldFlag/*�ɱ��*/, !oldFlag/*���±��*/); // ����ڴ˲���֮ǰ��markRef CAS �����Σ�����û�з�ֹABA���⣿
		
		if(b)
		{
			System.out.println("CAS success");
		}
		else
		{
			System.out.println("CAS fail");
		}
	}
	

	/**
	 * AtomicStampedReference
	 * ���ڶ���CAS������int���λ�ġ�ֻҪint������������ϱ�֤�������ABA����
	 */
	static void testAtomicStampedReference()
	{
		Data old = new Data(0,0);
		AtomicStampedReference<Data> ref = new AtomicStampedReference<Data>(old,0);
		
		int oldStamp = ref.getStamp();
		
		// try CAS
		boolean b = ref.compareAndSet(old, new Data(1,1), oldStamp, oldStamp+1); // ֻҪ��֤ÿ��CAS���Stamp���ӣ��ɾ��Ա�֤�������ABA���⡣�������˵�ˡ�
		
		if(b)
		{
			System.out.println("CAS success");
		}
		else
		{
			System.out.println("CAS fail");
		}
	}
	
}

class Data
{
	public int a;
	public int b;
	
	public Data(int a,int b)
	{
		this.a=a;
		this.b=b;
	}
}
