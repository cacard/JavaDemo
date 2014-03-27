/**
 * ���ڲ��������Լ�GC����
 */

package com.cacard.javademo;

public class PerformanceTest {

	public static void main(String[] args)
	{
		Demo1();
	}
	
	/**
	 * ����ʹ��VisualVM��VisualGCֱ�۲鿴GC����
	 */
	public static void Demo1()
	{
		// ��פ����
		// ����� �����䵽�����
		final byte[] old = new byte[102400000];
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true)
				{
					byte[] b = new byte[1024000];// ��������
					writeMsg("created a byte array.");
					int i=old.length;// �Գ�פ�������ã���ʾ��Ҫ��GC
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
	}
	
	public static void writeMsg(String msg)
	{
		System.out.println(msg);
	}
	
}
