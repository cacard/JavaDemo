package com.cacard.jvm;

public class NestedInterface {
	public static interface MyInterface {
		
	}
	
	public class MyClass {
		int a;
		public void method () {
			final int b = 0;
			final int c = 1;
			new Runnable(){
				@Override
				public void run() {
					int x = a;
					x = b;
					x = c;
				}};
		}
	}
}
