/**
 * 泛型测试
 */

package com.cacard.javalang;

import java.io.Serializable;

public class GenericType {

	public static void main(String[] args) {

		GenericSub1 obj = new GenericSub1();
		GenericSub1 o = GenericType.<GenericSub1> testMethod(GenericSub1.class);

	}

	static void testBasic() {

		// RawType
		boolean b0 = new GenericClass1() instanceof Object; // true
		boolean b1 = new GenericClass1() instanceof GenericClass1; // true,
																	// 原型的instance是原型类的instance
		boolean b2 = new GenericClass1<SomeClass>() instanceof GenericClass1; // true
		boolean b3 = new GenericClass1<SomeClass>() instanceof GenericClass1<?>; // true
		// boolean b4 = new GenericClass1<SomeClass>() instanceof
		// GenericClass1<SomeClass>; // instanceof 右侧不允许具体泛型，因为运行期擦除

		GenericClass1<SomeClass> t = new GenericClass1<>();
	}

	public static <T extends GenericBase> T testMethod(Class<?> c) {
		System.out.println(c.getSimpleName());

		GenericSub1 obj = new GenericSub1();
		return (T) obj;
	}

}

class SomeClass implements Serializable {

}

class SomeThread extends Thread {

}

class GenericClass1<T extends Serializable> {

}

class GenericBase {
}

class GenericSub1 extends GenericBase {
}

class GenericSub2 extends GenericBase {
}
