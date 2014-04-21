/**
 * Atomic 原子操作测试
 * 对象CAS
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
		// 用于多线程下共享变量的的自增，实现同步（线程安全）
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
		
		// 用于比较更新，即每次修改的量不是1（非递增、递减）
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
		// 修改并写回
		atomicForABA.getAndIncrement();	// =1
		// 修改成初始值
		atomicForABA.getAndDecrement(); // =0
		
		// CAS 成功，其实此时的0已经非彼时的0
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
		System.out.println(old==old1);	// true,说明通过 get 得到的对象的指针与源对象相同
		
		old.a=100;	// 修改 old对象的内部字段，CAS成功。因为对象的指针未变
		//old=new Data(1000,2000); // old指向新对象，CAS失败
		// 对象进行CAS
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
	 * 带有一个bool附属标记的对象CAS
	 * 一个bool标记可以防止ABA吗？
	 */
	static void testAtomicMarkableReference()
	{
		Data old = new Data(0,0);
		AtomicMarkableReference<Data> markRef = new AtomicMarkableReference<Data>(old,true);
		
		boolean oldFlag = markRef.isMarked(); // 旧对象的附属标记位
		
		Data newObj = new Data(1,1);	// 新对象
		boolean b = markRef.compareAndSet(old/*旧对象*/, newObj, oldFlag/*旧标记*/, !oldFlag/*更新标记*/); // 如果在此操作之前，markRef CAS 了两次，岂不是没有防止ABA问题？
		
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
	 * 用于对象CAS，带有int标记位的。只要int不溢出，理论上保证不会出现ABA问题
	 */
	static void testAtomicStampedReference()
	{
		Data old = new Data(0,0);
		AtomicStampedReference<Data> ref = new AtomicStampedReference<Data>(old,0);
		
		int oldStamp = ref.getStamp();
		
		// try CAS
		boolean b = ref.compareAndSet(old, new Data(1,1), oldStamp, oldStamp+1); // 只要保证每次CAS后对Stamp增加，可绝对保证不会出现ABA问题。溢出就另说了。
		
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
