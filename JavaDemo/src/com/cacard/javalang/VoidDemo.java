/**
 * 
 * void �ǹؼ��֣���ʾ������ֱ��return��
 * Void ���޷�ʵ���������ͣ�һ�����������е����ͺ��ԡ���ֵһ���ʹ��null��
 * 
 */

package com.cacard.javalang;

public class VoidDemo {

	public static void main(String[] args) {

		Void v1 = null;
		// void v2 = null;

		m(v1);
		mustReturnVoid();
	}

	/**
	 * ��������ΪVoid
	 * 
	 * @param m
	 * @return
	 */
	private static void m(Void m) {
		System.out.println(m);

		return;
	}

	/**
	 * ��������Ϊvoid
	 * 
	 * @return
	 */
	private static Void mustReturnVoid() {
		Void v = null;
		return v;
	}

}
