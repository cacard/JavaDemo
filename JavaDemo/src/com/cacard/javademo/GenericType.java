package com.cacard.javademo;

import java.io.Serializable;

public class GenericType {

	public static void main(String[] args)
	{
		M(SomeClass.class);
	}
	
	static void M(Class<? extends Serializable> c)
	{
		
	}
	
	
}

class SomeClass<T extends Object> implements Serializable
{
	
}


