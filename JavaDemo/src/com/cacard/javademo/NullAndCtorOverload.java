package com.cacard.javademo;

public class NullAndCtorOverload {

	public static void main(String[] args)
	{
		Person p = new Person();
		p.M((String)null);
	}
	
}

class Person
{
	public Person(){}
	
	public Person(Person p)
	{
		System.out.println("(Person p)");
	}
	
	public Person(String s)
	{
		System.out.println("(String s)");
	}
	
	public void M(Person p)
	{
		
	}
	
	public void M(String s)
	{
		
	}
}