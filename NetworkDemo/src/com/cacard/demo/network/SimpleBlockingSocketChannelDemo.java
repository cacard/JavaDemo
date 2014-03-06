/**
 * ���Է�����Ϣ
 * 1 �ͻ��ˣ�Socket������ˣ� Blockingģʽ�µ�ServerSocketChannel��
 * 2 �ͻ��ˣ�Blocingģʽ�µ�SocketChannel������ˣ� Blockingģʽ�µ�ServerSocketChannel��
 */

package com.cacard.demo.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SimpleBlockingSocketChannelDemo {

	public static void main(String[] args) {
		startBlockingServerChannel();
		
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		startBlockingClinetUsingSocketChannel();
	}

	/**
	 * ����Socket����ServerSocketChannel
	 */
	public static void startBlockingClinetUsingSocket() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				Socket s = null;
				try {
					System.out.println("[client]start a client socket:");
					s = new Socket("127.0.0.1", 90);
					
					// write data
					OutputStream stream = s.getOutputStream();
					stream.write("Hello".getBytes("UTF-8"));

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
	
	/**
	 * ����SocketChanel����ServerSocketChannel
	 */
	public static void startBlockingClinetUsingSocketChannel() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				SocketChannel s = null;
				try {
					System.out.println("[client]start a client socket:");
					s = SocketChannel.open();
					//s.configureBlocking(true);
					System.out.println("[client]try connect...");
					s.connect(new InetSocketAddress("127.0.0.1", 90));
		
					System.out.println("[client]after connect");

					ByteBuffer src = ByteBuffer.allocate(20);
					src.put("Hello".getBytes("UTF-8"));
					
					src.flip();// !Ϊ��׼����
					
					s.write(src);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						s.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	/**
	 * ����ģʽ�µ�ServerSocketChannel
	 */
	public static void startBlockingServerChannel() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				ServerSocketChannel serverSocketChannel = null;
				try {
					while (true) {
						serverSocketChannel = ServerSocketChannel.open();
						serverSocketChannel.configureBlocking(true);
						serverSocketChannel.bind(new InetSocketAddress(90));

						System.out.println("[server]accepting...");
						SocketChannel client = serverSocketChannel.accept();

						ByteBuffer dst = ByteBuffer.allocate(1024);
						client.read(dst);

						String result = new String(dst.array(), "UTF-8");

						System.out.println("->" + result);
						
						serverSocketChannel.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}
	
	
	
	

	
	
}
