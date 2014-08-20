/**
 * 静态初始化块和实例初始化块
 * 
 * - 父类先被加载，先自行 static{}
 * - 实例初始化块早于ctor()，实例初始化块可以看做C++中的变量初始化列表。
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
