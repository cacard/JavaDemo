/**
 * 静态初始化块和实例初始化块
 * 
 * - 父类先被加载，先自行 static{}
 * - 实例初始化块早于ctor()
 * - 实例初始化块与字段的初始化先后顺序是按次序进行
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

	// 以下，变量初始化与实例初始化块测试
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
