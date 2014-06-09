/**
 * @author licunqing
 * 
 * JNI Demo #1��HelloWorld
 * 
 * 1 ����Java���Լ�����ʹ�õ���Native������
 * 2 ����C������Ҫ�õ���ͷ�ļ�����srcĿ¼��ִ��:javah -jni com.xxx.HelloWorld
 * 3 ʵ��ͷ�ļ���
 * 4 ����dll��
 * windows�£�cl -I"c:\Program Files\Java\jdk1.7.0_51\include" -I"C:\Program Files\Java\jdk1.7.0_51\include\win32" -MD -LD HelloWorld.c -FeHelloWord.dll
 * 5 ���� (���ɵ�dll�ļ��ŵ� jdk/bin�� )
 * 
 */

package com.cacard.jni;

public class HelloWorld {

	/**
	 * Native Method
	 */
	private native void print();
	
	public static void main(String[] args){
		new HelloWorld().print();
	}
	
	/**
	 * Load Library
	 */
	static{
		System.loadLibrary("HelloWorld"); // xxx.dll / xxx.so
	}
	
}
