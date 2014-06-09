/**
 * @author licunqing
 * 
 * JNI Demo #1：HelloWorld
 * 
 * 1 创建Java类以及声明使用到的Native方法。
 * 2 创建C代码需要用到的头文件。在src目录下执行:javah -jni com.xxx.HelloWorld
 * 3 实现头文件。
 * 4 生成dll。
 * windows下：cl -I"c:\Program Files\Java\jdk1.7.0_51\include" -I"C:\Program Files\Java\jdk1.7.0_51\include\win32" -MD -LD HelloWorld.c -FeHelloWord.dll
 * 5 运行 (生成的dll文件放到 jdk/bin中 )
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
