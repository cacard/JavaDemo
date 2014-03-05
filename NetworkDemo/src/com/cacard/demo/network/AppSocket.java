package com.cacard.demo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class AppSocket {

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
	
	public static void startServerSocket()
	{
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(90);
					
					while(true)
					{
						System.out.println("accepting...");
						ss.accept();
						System.out.println("accepted...");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}}).start();
		
	}
	
	public static void startClient()
	{
		for(int i=0;i<10;i++)
		{
			System.out.println("thread "+i);
			
			new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						System.out.println("start a client socket:");
						Socket s = new Socket("127.0.0.1",90);
						
						System.out.println("close a client socket");
						s.close();
					} catch (Exception e) {
						e.printStackTrace();
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
