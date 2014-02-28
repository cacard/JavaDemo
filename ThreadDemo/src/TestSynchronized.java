/**
 * author:licunqing
 * test
 * synchronized method/synchronized block/synchronized locker
 */

import java.util.ArrayList;
import java.util.List;


public class TestSynchronized {
	
	public static int a=0;
	public static byte[] locker = new byte[0];

	public static void main(String[] args)
	{
		final TestSynchronized app = new TestSynchronized();
		
		List<Thread> list = new ArrayList<Thread>();
		for(int i=0;i<100;i++)
		{
			list.add(new Thread(new Runnable(){

				@Override
				public void run() {

					app.Method();

					
				}}));
		}
		
		// start & join
		for(Thread t : list)
		{
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println(a);
	}
	
	public /*synchronized*/ void Method()
	{
		synchronized(locker)
		{
			a++;
		}
	}
	
}


