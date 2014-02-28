import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestExecutor {

	public static void main(String[] args)
	{
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<10;i++)
				{
					System.out.println("in thread:"+Thread.currentThread().getId());
					
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}});
		
		service.
		
		service.shutdown();
		
	}
	
}
