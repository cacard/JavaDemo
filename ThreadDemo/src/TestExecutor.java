import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestExecutor {

	public static void main(String[] args)
	{
		ExecutorService service = Executors.newCachedThreadPool();
		Runnable thread = new Thread(new MyThread());
		
		testFuture(service,thread);
	}
	
	public static void testExecute(ExecutorService service,Runnable thread)
	{
		service.execute(thread);
		service.shutdownNow();		
	}
	
	public static void testFuture(ExecutorService service,Runnable thread)
	{
		Future f = service.submit(thread);
		Future<Integer> f2 = service.submit(new MyCallable());
		
//		try {
//			Thread.currentThread().sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		

			try {
				System.out.println("get:");
				System.out.println(f2.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
}

class MyCallable implements Callable<Integer>
{
	@Override
	public Integer call() throws Exception {
		for(int i=0;i<1000;i++)
		{
			
		}
		return 235;
	}
	
}

class MyThread implements Runnable
{

	@Override
	public void run() {
		for(int i=0;i<10;i++)
		{
			System.out.println("in thread:"+Thread.currentThread().getId());
			System.out.println("i:"+i);
			
			
		}
		
	}
	
}