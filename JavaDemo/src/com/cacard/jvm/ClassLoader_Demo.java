package com.cacard.jvm;

public class ClassLoader_Demo {

	private void getClassLoaderParent() {
		ClassLoader cl = this.getClass().getClassLoader();

		while (cl != null) {
			System.out.println("->" + cl.toString());
			cl = cl.getParent();
		}
		
		// output:
		// AppClassLoader
		// ExtClassLoader
		// 

	}

	public static void main(String[] args) {

		ClassLoader_Demo o = new ClassLoader_Demo();

		o.getClassLoaderParent();

	}
	

}
