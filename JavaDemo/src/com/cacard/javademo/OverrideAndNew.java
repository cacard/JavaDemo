package com.cacard.javademo;

public class OverrideAndNew {

	public static void main(String[] args)
	{
		new Sub().M();
		((Base)new Sub()).M();
	}
	
}

class Base
{
	public void M(){
		System.out.println("Base->M");
	}
	
	public static void N()
	{
		System.out.println("Base->N");
	}
}

class Sub extends Base
{
	@Override
	public void M(){
		System.out.println("Sub->M");
	}
	
	public static void N()
	{
		System.out.println("Sub->N");
	}
}
