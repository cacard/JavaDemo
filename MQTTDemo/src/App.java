/**
 * MQTT Blocking Example
 */

import org.eclipse.paho.client.mqttv3.MqttException;
import com.cacard.pojo.*;

public class App {

	// Default settings:
	static boolean quietMode 	= false;
	static String action 		= "publish";
	static String topic 		= "";
	static String message 		= "Hello";
	static int qos 			= 1;
	static String broker 		= "localhost";
	static int port 			= 1883;
	static String clientId 	= "someid"; // 同一台机器时候 pub/sub 的 clientid要不同
	static String clientId2 = "someid2";
	static String subTopic		= "im/licunqing/#";
	static String pubTopic 	= "im/licunqing";
	static boolean cleanSession = true;			// Non durable subscriptions
	static boolean ssl = false;
	static String password = null;
	static String userName = null;
	static String protocol = "tcp://";
	static String brokeUrl=protocol + broker + ":" + port;
	
	public static void main(String[] args)
	{
		
		if(args[0].equals("sub"))
		{
			startReceverService();
		}

		if(args[0].equals("pub"))
		{
			startClient("hello,send to licunqing");
		}
	}
	
	/**
	 * publisher
	 * @param msg
	 */
	public static void startClient(final String msg)
	{
		new Thread(new Runnable(){

			public void run() {
				MyClient sender=null;
				try {
					sender = new MyClient(brokeUrl,clientId,cleanSession,quietMode,userName,password);
					
					sender.publish(pubTopic, qos, msg.getBytes());
					
				} catch (MqttException e) {
					e.printStackTrace();
				}
				
			}}).start();
	}
	
	/**
	 * Subscriber
	 */
	public static void startReceverService()
	{
		new Thread(new Runnable(){

			public void run() {
				
				while(true)
				{
					System.out.println("[recever listening...]");
					MyClient sender=null;
					try {
						sender = new MyClient(brokeUrl,clientId2,cleanSession,quietMode,userName,password);
						sender.subscribe(subTopic, qos);
						
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
	}
	
	
}
