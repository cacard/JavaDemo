package com.cacard.jvm;

class Outer // �ⲿ��
{
	public void SomeMethod() // �ⲿ���ĳ��ʵ������
	{
		final int x = 1; // final�������ֲ������������
		final int y = 2; // ��final���������������ٸ��ģ�Java8��Ϊ��effectively-final������java8�У��ֲ���Ҳ����������

		class Inner // �ֲ���
		{
			void SomeMethod() {
				int a = x;
				int b = y; // java8
			}
		}
	}
}
