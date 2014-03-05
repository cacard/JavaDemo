package com.cacard.demo.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class AppSocketChannel {

	public static void main(String[] args)
	{
		
		//startServerSocket();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testSocketChannel();
		
	}
	
	static void startServerSocket()
	{
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(90);
					System.out.println("server socket is accepting ...");
					ss.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}}).start();
		

	}
	
	static void testServerSocketChannel()
	{
		Selector selector = null;
		
		try {
			selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	static void testSocketChannel()
	{
		try {
			SocketChannel sc = SocketChannel.open();
			
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("localhost",90));
			
			while(true)
			{
				System.out.println("loop");
				if(sc.finishConnect())
				{
					System.out.println("finishConnect()");
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
