/**
 * LocalClass（局部类）是特殊的InnerClass
 * 
 * 		LocalClass同样持有Outer的this，局部类对象依赖于外部对象。
 * 		LocalClass可以直接访问Outer的Member。
 * 		LocalClass访问所在block内的局部变量时，需要局部变量声明为final，Java8可为effectively-final（变量初始化后不再被改变）。
 */

package com.cacard.javalang;

public class InnerClass_LocalClass {

	private int a = 1; // LocalClass可直接使用外部类成员

	private void hello() {
		System.out.println("hello from outer.");
	}

	public void method(final int c) // 参数相当于局部变量，LocalClass访问时需要加final。
	{
		final int b = 2; // LocalClass访问外围Block的变量（即局部变量），需要final。
		class LocalClass {
			public void hello() {
				System.out.println("hello,a=" + a + ",b=" + b + "," + c);
				InnerClass_LocalClass.this.hello();
			}
		}
	}

	// Java SE8的特殊情况
	public void methodUsingJavaSE8(int a, String b) {
		int c = 1;

		class LocalClass {
			public void hello() {
				// Java8编译通过
				// System.out.println("hello,a=" + a + ",b=" + b + "," + c);
			}
		}
	}
}
