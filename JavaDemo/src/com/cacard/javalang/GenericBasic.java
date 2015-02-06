package com.cacard.javalang;

import java.io.Serializable;

public class GenericBasic {
	/**
	 * extends边界之extends一个接口：关键词并不仅仅代表“继承自”，也可代表“实现了”（接口），边界包含自身。
	 * 
	 * @param c
	 */
	static void TestExtendsFromInterface(Class<? extends Serializable> c) {
	}

	/**
	 * extends一个超类
	 * 
	 * @param c
	 */
	static void TestExtendsFromSuperClass(Class<? extends SomeThread> c) {

	}
}
