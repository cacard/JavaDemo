import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class App {

	static String QUEUE_NAME = "test";

	public static void main(String[] args) {

		
		startSub();
		
		for(int i=0;i<10;i++)
		{
			startPub();
			
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void startSub() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					ConnectionFactory factory = new ConnectionFactory();
					factory.setHost("localhost");
					Connection connection = factory.newConnection();
					Channel channel = connection.createChannel();

					channel.queueDeclare(QUEUE_NAME, false, false, false, null); // 同样，看一下Queue是否存在

					QueueingConsumer consumer = new QueueingConsumer(channel);
					channel.basicConsume(QUEUE_NAME, true, consumer);

					while (true) {
						QueueingConsumer.Delivery delivery = consumer
								.nextDelivery(); // 阻塞，直到接收到一条消息
						String message = new String(delivery.getBody());
						System.out.println(" [x] Received '" + message + "'");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	public static void startPub() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					ConnectionFactory factory = new ConnectionFactory();
					factory.setHost("localhost");
					Connection connection = (Connection) factory
							.newConnection();
					Channel channel = connection.createChannel();

					channel.queueDeclare(QUEUE_NAME, false, false, false, null); // (如果没有就)创建Queue
					String message = "Hello World!";
					channel.basicPublish("", QUEUE_NAME, null,
							message.getBytes()); // 以byte的方式发布
					System.out.println(" [x] Sent '" + message + "'");

					channel.close();
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

}
