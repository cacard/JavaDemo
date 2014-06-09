/**
 * @author licunqing
 * 
 * JNI Demo #2:echo String
 * 
 * Java��Native����String,Native���ظ�String
 * 
 */

package com.cacard.jni;

public class EchoString {

	private native String Echo(String str);
	
	public static void main(String[] args){
		String strFromNative = new EchoString().Echo("Hello");
		System.out.println(strFromNative);
	}
	
	static{
		System.loadLibrary("EchoString");
	}
}
