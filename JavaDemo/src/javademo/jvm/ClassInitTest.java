/**
 * 类初始化测试
 * 
 * 	- final static 变成调用方的常量池字段
 * 	- 调用从父类继承下来的static字段，则导致父类加载和初始化，而非子类，【被动引用】
 * 	- 类数组属于被动引用
 */

package javademo.jvm;


public class ClassInitTest {
	

	public static void main(String[] args) {
		
		//System.out.println(ClassY.a);
		
		ClassX[] classXArray=new ClassX[2]; // 被动引用

	}
	
	public static class ClassX{
		
		static{
			System.out.println("ClassX static init");
		}
		
		public static final int a=1;
	}
	
	public static class ClassY extends ClassX{
		static{
			System.out.println("ClassY static init");
		}
	}


}
