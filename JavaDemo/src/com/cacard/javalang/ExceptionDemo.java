/**
 * Exception & Error
 * 
 * ���
 * Throwable
 * 		Error
 * 		Exception
 * 			RuntimeException
 * 
 * ������쳣��Exception
 * �Ǽ�����쳣��RuntimeException/Error
 * 
 * ErrorΪJVM�����쳣����Ϊ������
 * RuntimeExceptionΪ����ʱ�쳣��ͬʱErrorҲ���Կ�������ʱ�쳣��
 * 
 * ----------------
 * �쳣�������׳�
 * 1 ֱ���׳��쳣��throw e;
 * 2 �����쳣���½��쳣���׳�:throw new Exception(e); ����ͨ��Cause�쳣��ȡԭʼ�쳣��
 * 
 * ---------------
 * �������������쳣����
 * ����������е�catch/finally�ֿ����׳��쳣�����ٰ�װһ��try/catch�ɡ�
 * 
 * ---------------
 * �쳣��
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
	 * UncheckedException(RuntimeException) �ɽ���try/catch��Ҳ�ɲ�catch
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
	 * Catch Error ����Throwable����catch
	 */
	private static void testError() {
		try {
			throw new Error("error");
		} catch (Throwable e) {
			System.out.println("->" + e.getMessage());
		}
	}

	/**
	 * �����׳��쳣��ֱ���׳�
	 * 
	 * 
	 * @throws Exception
	 */
	private static void reThrowException1() {

		try { // ʹ��Ƕ�ף���׽catch�������׳����쳣
			try {
				throw new Exception("exception");
			} catch (Exception e) {
				throw e; // �����׳���statckTraceΪԭʼ�쳣stack��
				// e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����׳��쳣�����´������쳣�׳���ԭʼ�쳣��ջ��ʧ����ͨ��Cause�쳣��ȡԭʼ�쳣��
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
	 * �쳣��
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
