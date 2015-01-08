/**
 * ��̬��ʼ�����ʵ����ʼ����
 * 
 * - �����ȱ����أ������� static{}
 * - ʵ����ʼ��������ctor()
 * - ʵ����ʼ�������ֶεĳ�ʼ���Ⱥ�˳���ǰ��������
 * 
 */

package com.cacard.javalang;

public class InitBlock {

	public static void main(String[] args) {

		new InitBlockSub();
	}

}

class InitBlockBase {
	static {
		System.out.println("base static{}");
	}

	{
		System.out.println("base instance{}");
	}

	public InitBlockBase() {
		System.out.println("base ctor");
	}
}

class InitBlockSub extends InitBlockBase {
	static {
		System.out.println("sub static{}");
	}

	// ���£�������ʼ����ʵ����ʼ�������
	int a = 1;
	int b = getB();
	{
		System.out.println("sub instance{}");
	}
	int c = getC();

	public InitBlockSub() {
		System.out.println("sub ctor");
	}

	private int getB() {
		System.out.println("sub getB()");
		return 2;
	}

	private int getC() {
		System.out.println("sub getC()");
		return 3;
	}
}
