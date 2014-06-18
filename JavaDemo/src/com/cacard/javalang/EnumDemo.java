package com.cacard.javalang;

public class EnumDemo {

	public static void main(String[] args){
		MyEnum m = MyEnum.values()[1];
		int count = m.ordinal();
		
		System.out.println(m.toString()+","+count);
	}
	
	enum MyEnum
	{
		A,
		B
		

	}
}



