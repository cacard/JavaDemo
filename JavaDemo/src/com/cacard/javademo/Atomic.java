package com.cacard.javademo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Atomic {

	public static void main(String[] args)
	{
		testRef();
	}
	
	static void testInt()
	{
		AtomicInteger atom = new AtomicInteger(1);
		boolean r = atom.compareAndSet(11, 2);
		
		System.out.println(atom.get());
	}
	
	static void testRef()
	{
		Data old = new Data(1,1);
		AtomicReference<Data> atomRef = new AtomicReference<Data>(old); 
		Data oldAgain = atomRef.get();
		System.out.println(old==oldAgain);
		
		Data updateDate = new Data(2,3);
		//Data updated = atomRef.getAndSet(updateDate);
		boolean b = atomRef.compareAndSet(old, updateDate);
		
		Data updated2= atomRef.get();
		System.out.println(old==updated2);
		System.out.println(updated2.a);
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
