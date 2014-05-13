package com.cacard.javalang;

public class Person2 {

	private String name;
	private int age;
	
	public Person2()
	{
		System.out.println("->ctor()");
	}
	
	public Person2(String name,int age)
	{
		System.out.println("->ctor(String name,int age)");
		this.name=name;
		this.age=age;
	}
	
	public void instanceMethod()
	{
		System.out.println("this is instance method.");
	}
	
	public void instanceMethodWithParams(String name,int age)
	{
		System.out.println("this is instance instanceMethodWithParams.name="+name+",age="+age);
	}
	
	public static void staticMethod()
	{
		System.out.println("this is static method.");
	}
	
	public void staticMethodWithParams(String name,int age)
	{
		System.out.println("this is instance instanceMethodWithParams.name="+name+",age="+age);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
