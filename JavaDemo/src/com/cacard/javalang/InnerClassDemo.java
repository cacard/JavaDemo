/**
 * 
 * Ƕ���ࣨNested�����ڲ��ࣨInner��
 * 
 * ����Ķ���λ���������ɷ�Ϊ TopLevelClass / NestedClass��
 * �����Ƕȿ�Ƕ����
 ** NestedClass
 *     - MemberClass ��Ա��
 *     - LocalClass ������
 *     - AnonymousClass ������
 ** NestedClass
 *     - InnerClass �ڲ���
 *          - Non-static Member class
 *          - LocalClass
 *          - AnonymousClass
 *     - Static Member class
 * 
 ** ��Ա������֣��Ǿ�̬��Ա��;�̬��Ա�ࡣ���ھ�̬��Ա������Χ��û�й�ϵ�������Ƕ�����������ѣ������Բ�������Χ����ڲ��ࡣ
 ** ��Ա�ӿ�(MemberInterface)����ʽ��static�࣬���Բ���InnerClass��
 * 
 * InnertClass����
 ** InnertClass��Inner��OuterClass��Inner��
 ** ������̬��Ա��������final�ģ������Ǽ̳���������ķ�final��̬��Ա��
 ** ������̬���캯��
 ** �ڲ���������ӿڣ��ӿ�����ʽ��̬�ġ��ӿ�ֻ��������top-level���л���interface�У�
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
	 * �ⲿ��ľ�̬�����������ⲿʵ����ȡ���ڲ�ʵ���������ڲ���
	 */
	public static void staticMethod() {
		InnerClassDemo.MyInnerClass inner = new InnerClassDemo().new MyInnerClass(); // ��������﷨
		inner.say();
	}

	/**
	 * �ⲿ���ʵ��������ֱ�Ӵ����ڲ���ʵ��
	 */
	public void instanceMethod() {
		MyInnerClass inner = new MyInnerClass();
		inner.say();
	}

	/**
	 * Non-static Member Class is Inner
	 */
	class MyInnerClass {

		// public static int a=0; /*������̬��Ա��������final��*/
		public static final int b = 0;

		/*
		 * ������̬���캯�� static{ a=1; }
		 */

		/*
		 * �ڲ���������ӿڣ��ӿ�ֻ��������top-level���л���interface�У� �ӿ�����ʽ��̬�ġ�
		 * Non-StaticMemberClassͬ���������徲̬���� public interface MyFace{}
		 */

		public void say() {
			System.out.println("MyInnerClass.say()");
		}

	}

	/**
	 * �ӿ�����ʽ��̬�ģ����Բ�����InnerClass
	 * ���� static-member classһ������
	 */
	public interface MyFace {
		public interface MyFaceInner {
		}
	}

	/**
	 * StaticMemberClass����Inner��
	 * 
	 */
	public static class StaticMemberClass {

	}

}
