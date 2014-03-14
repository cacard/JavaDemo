package org.eclipse.paho.sample.mqttv3app;

import org.eclipse.paho.client.mqttv3.MqttException;

public class App {

	// Default settings:
	static boolean quietMode 	= false;
	static String action 		= "publish";
	static String topic 		= "";
	static String message 		= "Hello";
	static int qos 			= 2;
	static String broker 		= "localhost";
	static int port 			= 1883;
	static String clientId 	= "someid";
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
		
		startReceverService();
		

		//startClient("hello,send to licunqing");
	}
	
	public static void startClient(final String msg)
	{
		new Thread(new Runnable(){

			public void run() {
				MySender sender=null;
				try {
					sender = new MySender(brokeUrl,clientId,cleanSession,quietMode,userName,password);
					
					sender.publish(pubTopic, qos, msg.getBytes());
					
				} catch (MqttException e) {
					e.printStackTrace();
				}
				
			}}).start();
	}
	
	public static void startReceverService()
	{
		new Thread(new Runnable(){

			public void run() {
				
				while(true)
				{
					System.out.println("[recever listening...]");
					MySender sender=null;
					try {
						sender = new MySender(brokeUrl,clientId,cleanSession,quietMode,userName,password);
						
						//sender.publish(pubTopic, qos, "hello,send to licunqing".getBytes());
						
						sender.subscribe(subTopic, qos);
						
						
						
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
	}
	
	
}
