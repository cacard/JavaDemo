/**
 * simple socket demo
 * client -> server
 * author:licunqing
 */

package com.cacard.demo.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppSocket {

	private static int port=90;
	
	public static void main(String[] args)
	{
		startServerSocket();
		
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		startClient();
	}
	
	/**
	 * Start Server to linstening
	 */
	public static void startServerSocket()
	{
		new Thread(new Runnable(){
			@Override
			public void run() {
				
				ServerSocket serverSocket=null;
				try {
					
					serverSocket = new ServerSocket(port);
					while(true)
					{
						System.out.println("[server]server accepting...");
						Socket clientSocket = serverSocket.accept();
						System.out.println("[server]accepted");
						
						String result="";
						int bufferLen=1024;
						byte[] buffer = new byte[bufferLen];
						InputStream is = clientSocket.getInputStream();
						
						int len=is.read(buffer, 0, bufferLen);
						while(len>0)
						{
							result+=new String(buffer,"UTF-8").trim();
							len=is.read(buffer, 0, bufferLen);
						}
						
						System.out.println("->"+result);
						
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					try {
						serverSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}}).start();
	}
	
	/**
	 * start client 
	 */
	public static void startClient()
	{
		for(int i=0;i<10;i++)
		{
			new Thread(new Runnable(){

				@Override
				public void run() {
					
					Socket s = null;
					try {
						System.out.println("[client]start a client socket:");
						s = new Socket("127.0.0.1",port);
						
						// write data
						OutputStream stream = s.getOutputStream();
						stream.write("Hello".getBytes("UTF-8"));
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					finally
					{
						try {
							s.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}}).start();
			
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
