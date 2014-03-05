/**
 * socket demo
 * author:licunqing
 */

package com.cacard.demo.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppSocket {

	private static int port=90;
	
	public static void main(String[] args)
	{
		startServerSocket();
		
		// sleep
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
						
						// write data
						OutputStream os = clientSocket.getOutputStream();
						os.write("hehe".getBytes("UTF-8"));
						os.close();
						
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
		for(int i=0;i<1;i++)
		{
			System.out.println("thread "+i);
			
			new Thread(new Runnable(){

				@Override
				public void run() {
					
					Socket s = null;
					try {
						System.out.println("start a client socket:");
						s = new Socket("127.0.0.1",port);
						s.setSoLinger(true, 0);
						s.setTcpNoDelay(true);
						
						// TODO:如何实现Client端在写入数据之后，flush，然后read阻塞等待Server用这条连接返回的数据
						
						// write data
						OutputStream stream = s.getOutputStream();
						stream.write("Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.Hello.".getBytes("UTF-8"));
						
						stream.flush();
						System.out.println("[client][after flush]socket is close?"+s.isClosed());
						//stream.close();
						//System.out.println("[client][after close]socket is close?"+s.isClosed());
						
						// read (blocking)
						System.out.println("[client]try read");
						InputStream is = s.getInputStream();
						int len=is.read();
						while(len>=0)
						{
							System.out.println("[client]reading...");
							len=is.read();
						}
						
						
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
