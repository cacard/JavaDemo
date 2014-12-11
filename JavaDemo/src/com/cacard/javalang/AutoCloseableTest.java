package com.cacard.javalang;
public class AutoCloseableTest {

	public static void main(String[] args) {

		try (CanAutoCloseable c = new CanAutoCloseable()) {
			c.someMethod(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final class CanAutoCloseable implements AutoCloseable {

		public void someMethod(boolean exception) throws Exception {
			if (exception) {
				throw new Exception("yes");
			}
		}

		@Override
		public void close() throws Exception {
			System.out.println("->close()");

		}

	}

}
