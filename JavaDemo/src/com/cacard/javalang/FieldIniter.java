package com.cacard.javalang;

/**
 * ʵ���ֶγ�ʼ�������ֶεĳ�ʼ��˳���ĸ����ȣ���ִ���ĸ�
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
