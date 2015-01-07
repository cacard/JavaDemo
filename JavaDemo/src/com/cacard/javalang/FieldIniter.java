package com.cacard.javalang;

/**
 * 实例字段初始化块与字段的初始化顺序：哪个在先，先执行哪个
 */

public class FieldIniter {
	{
		System.out.println("{}1");
	}

	private int a = getA();
	private int c = 1;

	{
		System.out.println("{}2");
	}

	private int b = getB();

	public FieldIniter() {
		System.out.println("ctor()");
	}

	private int getA() {
		System.out.println("getA");
		return 1;
	}

	private int getB() {
		System.out.println("getB");
		return 2;
	}

	public static void main(String[] args) {
		new FieldIniter();
		int a = 1<<1;
		System.out.println(a);
	}

}
