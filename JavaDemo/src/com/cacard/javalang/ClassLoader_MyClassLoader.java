/**
 * �Զ����������
 * 
 * 
 */
package com.cacard.javalang;

public class ClassLoader_MyClassLoader extends ClassLoader {

	@Override
	public Class<?> findClass(String name){
		System.out.println("->findClass(),name="+name);
		
		return null; // Ӧ�÷���һ�������࣬����ͨ��defineClass��byte[]ת����һ����
	}
	
	
	// Test
	public static void main(String[] args) {
		ClassLoader_MyClassLoader loader = new ClassLoader_MyClassLoader();
		
		try {
			
			// ����Java�Ļ����࣬��Bootstrapֱ�Ӽ��ص�
			Class<?> clazz1 = loader.loadClass("java.lang.String");
			if(clazz1!=null)
				System.out.println("loaded class:"+clazz1.getName()
						+",Loader:"+clazz1.getClassLoader());
			
			// �����Զ����࣬��AppClassLoader����
			Class<?> clazz2 = loader.loadClass("com.cacard.javalang.ClassLoader_MyClassLoader");
			if(clazz2!=null){
				System.out.println("loaded class:"+clazz2.getName()
						+",Loader:"+clazz2.getClassLoader());
			}
			
			// ����һ�����ز������࣬�ɵ�ǰ�Զ���ClassLoader����
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
