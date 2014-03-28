/**
 * ���ڲ��������Լ�GC����
 */

package com.cacard.javademo;

public class PerformanceTest {
	
	private static final int _1M=1024*1024;
	private static final int _100K=1024*100;

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
		final byte[] old = new byte[_1M*10];
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true)
				{
					byte[] b = new byte[_1M];// ��������
					b=null;
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
