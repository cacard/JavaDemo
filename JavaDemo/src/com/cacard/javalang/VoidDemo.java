/**
 * 
 * void 是关键字，表示方法可直接return。
 * Void 是无法实例化的类型，一般用作泛型中的类型忽略。赋值一般可使用null。
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
	 * 方法参数为Void
	 * 
	 * @param m
	 * @return
	 */
	private static void m(Void m) {
		System.out.println(m);

		return;
	}

	/**
	 * 返回类型为void
	 * 
	 * @return
	 */
	private static Void mustReturnVoid() {
		Void v = null;
		return v;
	}

}
