package com.cacard.mqtt;

import java.util.Date;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * MQTT Blocking Example
 */
public class App {

	// Default settings:
	static boolean quietMode 	= false;
	static String action 		= "publish";
	static String topic 		= "";
	static String message 		= "Hello";
	static int qos 			= 1;
	static String broker 		= "10.32.24.52";
	static int port 			= 1883;
	static String clientId 	= "someid";
	static String clientId2 = "someid2";
	
	static String pubClientId="__client_id_p";
	static String subClientId="sub_client_id";
	static String commonClientId="__cid2__";
	
	static String subTopic		= "TalkMessage/#";
	static String pubTopic 	= "TalkMessage/licunqing";
	static boolean cleanSession = false;			// if false,means can durable 
	static boolean ssl = false;
	static String password = null;
	static String userName = null;
	static String protocol = "tcp://";
	static String brokeUrl=protocol + broker + ":" + port;
	
	public static void main(String[] args)
	{
		//startReceverService();
		startClient("hello,send to licunqing");
	}
	
	/**
	 * publisher
	 * @param msg
	 */
	public static void startClient(final String msg)
	{
		// 先创建一个订阅者，用来在rabbitmq中创建一个queue，这样就可以持久化信息了。
		new Thread(new Runnable(){

			public void run() {
				MyClient sender=null;
				try {
					
					sender = new MyClient(brokeUrl,commonClientId,cleanSession,quietMode,userName,password);
	
					
					for(int i=0;i<10;i++)
					{
						sender.publish(pubTopic, qos, (msg+"@"+new Date().getTime()).getBytes());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
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
				
				//while(true)
				{
					System.out.println("[recever listening...]");
					MyClient sender=null;
					try {
						sender = new MyClient(brokeUrl,commonClientId,cleanSession,quietMode,userName,password);
						sender.subscribe(subTopic, qos);
						
					} catch (MqttException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
	}
	
}
