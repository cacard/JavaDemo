/**
 * ServerSocketChannel Demo
 * author:licunqing
 */

package com.cacard.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AppSocketChannel {

	public static void main(String[] args) {

		new Thread(new MyServerSocketChannel()).start();

		startClient(90);

	}

	
	static void startClient(final int port) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				Socket s = null;

				while (true) {

					try {
						System.out.println("client socket try connect to port "
								+ port);
						s = new Socket("localhost", port);
						OutputStream stream = s.getOutputStream();
						String str = "hello" + port;
						stream.write(str.getBytes("UTF-8"));
						stream.close();

					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						System.out.println("close client socket");
						try {
							s.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					try {
						Thread.currentThread().sleep(16000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;

				}

			}
		}).start();

	}

	static void testSocketChannel() {
		try {
			SocketChannel sc = SocketChannel.open();

			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress("localhost", 90));

			while (true) {
				System.out.println("loop");
				if (sc.finishConnect()) {
					System.out.println("finishConnect()");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


class MyServerSocketChannel implements Runnable {

	@Override
	public void run() {
		Selector selector = null;

		ServerSocketChannel channel1 = null;
		ServerSocketChannel channel2 = null;

		try {
			selector = Selector.open();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			channel1 = ServerSocketChannel.open();
			channel1.configureBlocking(false);
			ServerSocket ss1 = channel1.socket();
			ss1.bind(new InetSocketAddress(90));

			channel2 = ServerSocketChannel.open();
			ServerSocket ss2 = channel2.socket();
			channel2.configureBlocking(false);
			ss2.bind(new InetSocketAddress(91));

			// register
			channel1.register(selector, SelectionKey.OP_ACCEPT);
			channel2.register(selector, SelectionKey.OP_ACCEPT);

			while (true) {
				System.out.println("using select blocking ...");
				selector.select(); // blocing until find one event
				Iterator iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();

					if (key.isValid() == false) {
						continue;
					}

					System.out.println(key.channel().toString());
					if (key.isAcceptable()) {
						// 变成了[connected local=/127.0.0.1:90
						// remote=/127.0.0.1:13441]
						ServerSocketChannel ssc = (ServerSocketChannel) key
								.channel();
						// 处理 accept
						SocketChannel client = ssc.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ
								| SelectionKey.OP_WRITE); // Register Read

					} else if (key.isReadable()) {
						System.out.println("can read");

						SocketChannel client = (SocketChannel) key
								.channel(); // 这个时候key对应的channel就是SocketChannel

						ByteBuffer bf = ByteBuffer.allocate(1000);
						int count = client.read(bf);

						System.out.println("read count=" + count);
						System.out.println("-->"
								+ new String(bf.array(), "UTF-8"));
						bf.flip();

						key.cancel();

					} else if (key.isWritable()) {
						System.out.println("can write");
					}

				}

				// try {
				// Thread.currentThread().sleep(3000);
				// } catch (InterruptedException e) {
				// e.printStackTrace();
				// }
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
