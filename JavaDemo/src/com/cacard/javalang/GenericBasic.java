package com.cacard.javalang;

import java.io.Serializable;

public class GenericBasic {
	/**
	 * extends�߽�֮extendsһ���ӿڣ��ؼ��ʲ������������̳��ԡ���Ҳ�ɴ���ʵ���ˡ����ӿڣ����߽��������
	 * 
	 * @param c
	 */
	static void TestExtendsFromInterface(Class<? extends Serializable> c) {
	}

	/**
	 * extendsһ������
	 * 
	 * @param c
	 */
	static void TestExtendsFromSuperClass(Class<? extends SomeThread> c) {

	}
}
