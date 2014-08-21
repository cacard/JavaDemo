/**
 * LocalClass���ֲ��ࣩ�������InnerClass
 * 
 * 		LocalClassͬ������Outer��this���ֲ�������������ⲿ����
 * 		LocalClass����ֱ�ӷ���Outer��Member��
 * 		LocalClass��������block�ڵľֲ�����ʱ����Ҫ�ֲ���������Ϊfinal��Java8��Ϊeffectively-final��������ʼ�����ٱ��ı䣩��
 */

package com.cacard.javalang;

public class InnerClass_LocalClass {

	private int a = 1; // LocalClass��ֱ��ʹ���ⲿ���Ա

	private void hello() {
		System.out.println("hello from outer.");
	}

	public void method(final int c) // �����൱�ھֲ�������LocalClass����ʱ��Ҫ��final��
	{
		final int b = 2; // LocalClass������ΧBlock�ı��������ֲ�����������Ҫfinal��
		class LocalClass {
			public void hello() {
				System.out.println("hello,a=" + a + ",b=" + b + "," + c);
				InnerClass_LocalClass.this.hello();
			}
		}
	}

	// Java SE8���������
	public void methodUsingJavaSE8(int a, String b) {
		int c = 1;

		class LocalClass {
			public void hello() {
				// Java8����ͨ��
				// System.out.println("hello,a=" + a + ",b=" + b + "," + c);
			}
		}
	}
}
