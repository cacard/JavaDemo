package imsystem.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * Server端，AIO
 */
public class Server {

	private int port;

	public Server(int port) {
		this.port = port;
	}

	/**
	 * 开始监听
	 * @throws IOException
	 */
	public void Start() throws IOException {
		final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel
				.open().bind(new InetSocketAddress(port));

		HelperLog.log("accepting...");
		server.accept(null/* attachment */,
				new CompletionHandler<AsynchronousSocketChannel, Void>() {

					@Override
					public void completed(AsynchronousSocketChannel result,
							Void attachment) {
						// accepting again
						server.accept(null, this);
						handlerAsynchronousSocketChannel(result);
					}

					@Override
					public void failed(Throwable exc, Void attachment) {
						HelperLog.log(exc.getMessage());
					}
				});
	}

	/**
	 * 有消息过来时处理消息
	 */
	public static void handlerAsynchronousSocketChannel(
			AsynchronousSocketChannel ch) {

		StringBuilder sb = new StringBuilder();

		ByteBuffer bfForWrite = ByteBuffer.allocate(1024);

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
			HelperLog.log(e.getMessage());
		}

		System.out.println("->" + sb.toString());
	}

	/**
	 * 停止Server端
	 */
	public void Stop() {

	}

}
