/**
 * ���ʼ������
 * 
 * 	- final static ��ɵ��÷��ĳ������ֶ�
 * 	- ���ôӸ���̳�������static�ֶΣ����¸�����غͳ�ʼ�����������࣬���������á�
 * 	- ���������ڱ�������
 */

package javademo.jvm;


public class ClassInitTest {
	

	public static void main(String[] args) {
		
		//System.out.println(ClassY.a);
		
		ClassX[] classXArray=new ClassX[2]; // ��������

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
