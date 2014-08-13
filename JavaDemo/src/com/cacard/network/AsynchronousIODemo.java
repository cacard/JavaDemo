/**
 * AIO simple
 */

package com.cacard.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import com.cacard.helper.*;

public class AsynchronousIODemo {

	public static void main(String[] args) {

		startServer();
		ThreadHelper.TrySleep(3000);
		startClient();

	}

	public static void startClient() {
		for (int i = 0; i < 10; i++) {
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

			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void startServer() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(90));

					System.out.println("[server]accepting...");
					server.accept(null/* attachment */, new CompletionHandler<AsynchronousSocketChannel, Void>() {

						@Override
						public void completed(AsynchronousSocketChannel result, Void attachment) {
							// accepting again
							server.accept(null, this);

							handlerAsynchronousSocketChannel(result);
						}

						@Override
						public void failed(Throwable exc, Void attachment) {
							exc.printStackTrace();

						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	public static void handlerAsynchronousSocketChannel(AsynchronousSocketChannel ch) {
		StringBuilder sb = new StringBuilder();

		ByteBuffer bfForWrite = ByteBuffer.allocate(2);

		try {
			int len = ch.read(bfForWrite).get();

			while (len != -1) {

				bfForWrite.flip(); // ready for reading

				// reading
				while (bfForWrite.hasRemaining()) {
					sb.append((char) bfForWrite.get());// do not use .getChar()
				}

				// ready for writing
				bfForWrite.clear();

				// write again
				len = ch.read(bfForWrite).get();

			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("->" + sb.toString());
	}

}
