package com.cacard.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * ���ĺͷ����޷�����ͬһ��ClientID��Client
 * @author licq
 *
 */
public class App2 {
	
	
	// Default settings:
	static boolean quietMode 	= false;
	static String action 		= "publish";
	static String topic 		= "";
	static String message 		= "Hello";
	static int qos 			= 1;
	static String broker 		= "223.203.209.52";//"10.32.24.52";
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
	
	

	private static MyClient _client = null;
	
	public static void main(String[] args){
		
		// create a client
		try {
			_client = new MyClient(brokeUrl,commonClientId,cleanSession,quietMode,userName,password);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
		//��ʼ����
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					_client.subscribe(subTopic, qos);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}).start();
		
		
		//ʹ��ͬһclient������Ϣ
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				for(int i=0;i<100;i++){
					
					//����
					try {
						_client.publish(pubTopic, qos, String.valueOf(i).getBytes());
					} catch (MqttException e1) {
						e1.printStackTrace();
					}
					
					//��ͣ
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
		
	}
	
}
