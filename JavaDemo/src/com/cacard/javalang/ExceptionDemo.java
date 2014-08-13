/**
 * Exception & Error
 * 
 * 层次
 * Throwable
 * 		Error
 * 		Exception
 * 			RuntimeException
 * 
 * 检查型异常，Exception
 * 非检查型异常，RuntimeException/Error
 * 
 * Error为JVM严重异常，称为“错误”
 * RuntimeException为运行时异常，同时Error也可以看做运行时异常。
 * 
 * ----------------
 * 异常的重新抛出
 * 1 直接抛出异常：throw e;
 * 2 基于异常，新建异常，抛出:throw new Exception(e); （可通过Cause异常获取原始异常）
 * 
 * ---------------
 * 构造器不能有异常声明
 * 如果构造器中的catch/finally又可能抛出异常，则再包装一个try/catch吧。
 * 
 * ---------------
 * 异常链
 * 
 */

package com.cacard.javalang;

public class ExceptionDemo {

	public static void main(String[] args) {
		testExceptionCause();
	}

	/**
	 * Catch Checked Exception
	 */
	private static void testCheckedException() {
		try {
			throw new Exception("Normal Exception");
		} catch (Exception e) {
			System.out.println("->" + e.getMessage());
		}
	}

	/**
	 * UncheckedException(RuntimeException) 可进行try/catch，也可不catch
	 */
	private static void testUncheckedException() {

		// throw new RuntimeException("Runtime Exception");

		try {
			throw new RuntimeException("Runtime Exception");
		} catch (Exception e) {
			System.out.println("->" + e.getMessage());
		}
	}

	/**
	 * Catch Error 采用Throwable进行catch
	 */
	private static void testError() {
		try {
			throw new Error("error");
		} catch (Throwable e) {
			System.out.println("->" + e.getMessage());
		}
	}

	/**
	 * 重新抛出异常：直接抛出
	 * 
	 * 
	 * @throws Exception
	 */
	private static void reThrowException1() {

		try { // 使用嵌套，捕捉catch中重新抛出的异常
			try {
				throw new Exception("exception");
			} catch (Exception e) {
				throw e; // 重新抛出，statckTrace为原始异常stack。
				// e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重新抛出异常：重新创建新异常抛出，原始异常堆栈丢失（可通过Cause异常获取原始异常）
	 * 
	 * @throws Exception
	 */
	private static void reThrowException2() {
		try {
			try {
				throw new Exception("exception");
			} catch (Exception e) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异常链
	 */
	private static void testExceptionCause() {
		Exception e1 = new Exception("e1");
		Exception e2 = new Exception("e2");

		e2.initCause(e1);

		try {
			throw e2;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause().printStackTrace();
		}
	}
}
