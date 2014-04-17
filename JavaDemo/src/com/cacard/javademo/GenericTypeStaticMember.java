/**
 * 类型的泛型不能应用在静态成员当中，因为静态成员属于当前类型，而当前类型只有一个，所以不能泛型化
 */

package com.cacard.javademo;

public class GenericTypeStaticMember<T> {

	//private static T t; // error
	
	//public static void SomeMethod(T t){} // error

	
}
