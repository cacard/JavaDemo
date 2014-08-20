/**
 * ��̬��ʼ�����ʵ����ʼ����
 * 
 * - �����ȱ����أ������� static{}
 * - ʵ����ʼ��������ctor()��ʵ����ʼ������Կ���C++�еı�����ʼ���б�
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

	{
		System.out.println("sub instance{}");
	}

	public InitBlockSub() {
		System.out.println("sub ctor");
	}
}
