/**
 * 用于测试性能以及GC过程
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
	 * 可以使用VisualVM的VisualGC直观查看GC过程
	 */
	public static void Demo1()
	{
		// 常驻对象
		// 大对象 被分配到老年代
		final byte[] old = new byte[_1M*10];
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true)
				{
					byte[] b = new byte[_1M];// 新生对象
					b=null;
					writeMsg("created a byte array.");
					int i=old.length;// 对常驻对象引用，表示不要被GC
					
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
