/**
 * 测试No-Blocing下的Socket：ServerSocketChannel
 */

package com.cacard.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.cacard.helper.*;

public class SimpleNoBlocingSocketChannel {

	public static void main(String[] args) {
		
		startNoBlockingServerChannel();
		
		ThreadHelper.TrySleep(5000);

		for (int i = 0; i < 10; i++) {
			startNoBlockingClinet();
			ThreadHelper.TrySleep(5000);
		}
	}
	
	/**
	 * 阻塞Socket调用ServerSocketChannel
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
	 * No-Blocking 客户端，使用輪詢
	 */
	public static void startNoBlockingClinet() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				SocketChannel s = null;
				try {
					
					
					System.out.println("[client]start a client socket:");
					s = SocketChannel.open();
					s.configureBlocking(false);
					s.connect(new InetSocketAddress("127.0.0.1", 90));

					while(true)
					{
						if(s.finishConnect())
						{
							System.out.println("[client]connected...");
							ByteBuffer src=ByteBuffer.allocate(200);
							src.put("Hello".getBytes("UTF-8"));
							src.flip();
							s.write(src);
							break;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						s.close();
						System.out.println("[client]close");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}


	/**
	 * No-Blocing 服务器
	 */
	public static void startNoBlockingServerChannel() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				ServerSocketChannel serverSocketChannel = null;
				Selector selector = null;

				try {

					selector = Selector.open();

					serverSocketChannel = ServerSocketChannel.open();
					serverSocketChannel.configureBlocking(false);
					serverSocketChannel.socket().bind(new InetSocketAddress(90));

					// register to selector
					serverSocketChannel.register(selector,
							SelectionKey.OP_ACCEPT);

					// 轮询selector
					while (true) {
						System.out
								.println("[server] Selector select and blocking ...");
						selector.select();// blocking,wait until 1 event

						Set<SelectionKey> keys = selector.selectedKeys();// not .keys()
						Iterator iter = keys.iterator();

						while (iter.hasNext()) {
							SelectionKey key = (SelectionKey)iter.next();
							iter.remove();

							// 以下判断最好使用 if else
							if (key.isValid() == false) {/* 如果key已经被取消，则返回 */
								continue;
							} else if (key.isAcceptable()) {
								SocketChannel client = ((ServerSocketChannel) key
										.channel()).accept();
								client.configureBlocking(false); // 继续设置为非阻塞模式（也可以是阻塞，直接处理）
								client.register(selector, SelectionKey.OP_READ);// 继续向Selector注册
							} else if (key.isReadable()) {
								SocketChannel client = (SocketChannel) key
										.channel();

								ByteBuffer bf = ByteBuffer.allocate(1000);
								int count = client.read(bf);
								System.out.println("-->"
										+ new String(bf.array(), "UTF-8"));
								// bf.flip();
								key.cancel();
							} else if (key.isWritable()) {
								// 不做处理
							}
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

}
