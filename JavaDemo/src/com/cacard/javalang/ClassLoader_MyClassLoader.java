/**
 * 自定义类加载器
 * 
 * 
 */
package com.cacard.javalang;

public class ClassLoader_MyClassLoader extends ClassLoader {

	@Override
	public Class<?> findClass(String name){
		System.out.println("->findClass(),name="+name);
		
		return null; // 应该返回一个具体类，比如通过defineClass将byte[]转化成一个类
	}
	
	
	// Test
	public static void main(String[] args) {
		ClassLoader_MyClassLoader loader = new ClassLoader_MyClassLoader();
		
		try {
			
			// 加载Java的基本类，由Bootstrap直接加载到
			Class<?> clazz1 = loader.loadClass("java.lang.String");
			if(clazz1!=null)
				System.out.println("loaded class:"+clazz1.getName()
						+",Loader:"+clazz1.getClassLoader());
			
			// 加载自定义类，由AppClassLoader加载
			Class<?> clazz2 = loader.loadClass("com.cacard.javalang.ClassLoader_MyClassLoader");
			if(clazz2!=null){
				System.out.println("loaded class:"+clazz2.getName()
						+",Loader:"+clazz2.getClassLoader());
			}
			
			// 加载一个加载不到的类，由当前自定义ClassLoader加载
			Class<?> clazz3 = loader.loadClass("com.cacard.javalang.ClassLoader_MyClassLoader1");
			if(clazz3!=null){
				System.out.println("loaded class:"+clazz3.getName()
						+",Loader:"+clazz3.getClassLoader());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
