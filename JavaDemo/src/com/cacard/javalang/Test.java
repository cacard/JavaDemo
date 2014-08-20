package com.cacard.javalang;

public class Test {

	public static void main(String[] args){
		
	}
	
	static class Clazz
	{
		public int var;
	}
	
	public static void M(Clazz c)
	{
		c.var=2;
	}
	
	static void test()
	{
		Clazz c = new Clazz();
		M(c);
		System.out.println(c.var);
	}
	
}

