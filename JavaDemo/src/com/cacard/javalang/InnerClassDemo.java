/**
 * 
 * 嵌套类（Nested）和内部类（Inner）
 * 
 * 从类的定义位置来看，可分为 TopLevelClass / NestedClass。
 * 两个角度看嵌套类
 ** NestedClass
 *     - MemberClass 成员类
 *     - LocalClass 本地类
 *     - AnonymousClass 匿名类
 ** NestedClass
 *     - InnerClass 内部类
 *          - Non-static Member class
 *          - LocalClass
 *          - AnonymousClass
 *     - Static Member class
 * 
 ** 成员类分两种：非静态成员类和静态成员类。由于静态成员类与外围类没有关系（仅仅是定义在这里而已），所以不算是外围类的内部类。
 ** 成员接口(MemberInterface)是隐式的static类，所以不算InnerClass。
 * 
 * InnertClass特性
 ** InnertClass的Inner是OuterClass的Inner。
 ** 不允许静态成员，除非是final的，或者是继承自其它类的非final静态成员。
 ** 不允许静态构造函数
 ** 内部不允许定义接口（接口是隐式静态的。接口只允许定义在top-level类中或者interface中）
 * 
 */

package com.cacard.javalang;

public class InnerClassDemo {

	public static void main(String[] args) {
		staticMethod();
		
		InnerClassDemo.MyFace face=null; 
		
	}

	private static void localClass(final boolean a) {
		class MyLocalClass implements MyFace {
			public void invoke() {
				System.out.println(a);
			}
		}
	}

	/**
	 * 外部类的静态方法必须由外部实例获取到内部实例来访问内部类
	 */
	public static void staticMethod() {
		InnerClassDemo.MyInnerClass inner = new InnerClassDemo().new MyInnerClass(); // 很奇葩的语法
		inner.say();
	}

	/**
	 * 外部类的实例方法可直接创建内部类实例
	 */
	public void instanceMethod() {
		MyInnerClass inner = new MyInnerClass();
		inner.say();
	}

	/**
	 * Non-static Member Class is Inner
	 */
	class MyInnerClass {

		// public static int a=0; /*不允许静态成员，除非是final的*/
		public static final int b = 0;

		/*
		 * 不允许静态构造函数 static{ a=1; }
		 */

		/*
		 * 内部不允许定义接口（接口只允许定义在top-level类中或者interface中） 接口是隐式静态的。
		 * Non-StaticMemberClass同样不允许定义静态方法 public interface MyFace{}
		 */

		public void say() {
			System.out.println("MyInnerClass.say()");
		}

	}

	/**
	 * 接口是隐式静态的，所以不属于InnerClass
	 * 可像 static-member class一样引用
	 */
	public interface MyFace {
		public interface MyFaceInner {
		}
	}

	/**
	 * StaticMemberClass不是Inner的
	 * 
	 */
	public static class StaticMemberClass {

	}

}
