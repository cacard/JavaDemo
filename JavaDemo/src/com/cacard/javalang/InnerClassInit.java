/**
 * 
 * �ڲ������ĳ�ʼ������
 * 
 * 		�ڲ��಻�ܰ���static��Ա������Ҳ���ܣ�Ҳû�б�Ҫ�����徲̬��ʼ����
 * 
 * 		��Ȼ���ȳ�ʼ����Χ����static block -> instance block -> ctor�����ٳ�ʼ���ڲ������
 * 
 */

package com.cacard.javalang;

public class InnerClassInit {

	{
		System.out.println("Outer -> instance init block"); // �ⲿ���ʵ����ʼ����
	}

	public InnerClassInit() {
		System.out.println("Outer -> ctor");
	}

	private class Inner // extends InnerClassInit
	{
		public Inner() {
			System.out.println("Inner -> ctor");
		}

		{
			System.out.println("Inner -> instance init block"); // �ڲ����ʵ����ʼ����
		}

	}

	public static void main(String[] args) {
		InnerClassInit.Inner inner = new InnerClassInit().new Inner();

	}

}
