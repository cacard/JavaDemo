package com.cacard.jvm;

class Outer // 外部类
{
	public void SomeMethod() // 外部类的某个实例方法
	{
		final int x = 1; // final变量，局部类可正常访问
		final int y = 2; // 非final变量，但后续不再更改，Java8称为“effectively-final”，在java8中，局部类也可正常访问

		class Inner // 局部类
		{
			void SomeMethod() {
				int a = x;
				int b = y; // java8
			}
		}
	}
}
