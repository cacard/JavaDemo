package com.cacard.javalang;

import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * DES���ܽ��� DES��һ�ֶԳƼ����㷨����ν�ԳƼ����㷨�������ܺͽ���ʹ����ͬ��Կ���㷨��DES�����㷨����IBM���о���
 * ����������������ʽ���ã�֮��ʼ�㷺���������ǽ�Щ��ʹ��Խ��Խ�٣���ΪDESʹ��56λ��Կ�����ִ�����������
 * 24Сʱ�ڼ��ɱ��ƽ⡣��Ȼ��ˣ���ĳЩ��Ӧ���У����ǻ��ǿ���ʹ��DES�����㷨�����ļ򵥽���DES��JAVAʵ�� ��
 * ע�⣺DES���ܺͽ��ܹ����У���Կ���ȶ�������8�ı���
 */
public class DES {
	public DES() {
	}

	private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String toHexStringNew(byte[] bytes) {
		int nBytes = bytes.length;
		StringBuilder hexString = new StringBuilder(2 * nBytes);
		for (int i = 0; i < nBytes; i++) {
			// Char for top 4 bits
			hexString.append(HEX[(0xF0 & bytes[i]) >>> 4]);
			// Bottom 4
			hexString.append(HEX[(0x0F & bytes[i])]);

		}
		return hexString.toString();
	}

	// ����
	public static void main(String args[]) {

		try {
			String key = "atXW1224";
			String source = "hello";

			byte[] ivb = new byte[8];
			IvParameterSpec iv = new IvParameterSpec(ivb);
			AlgorithmParameterSpec paramSpec = iv;

			SecretKeyFactory keyFactory;
			DESKeySpec dks = new DESKeySpec(key.getBytes("UTF8"));
			keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretkey = keyFactory.generateSecret(dks);
			// ����Cipher����
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// ��ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			// �ӽ���
			byte[] b = cipher.doFinal(source.getBytes("UTF8"));

			System.out.println("���ܺ�" + toHexStringNew(b));
			String base64 = Base64.encode(b);
			System.out.println("���ܺ�" + base64);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (1 == 1) {
			return;
		}

		// ����������
		String str = "hello";
		// ���룬����Ҫ��8�ı���
		String password = "atXW1224";

		byte[] result = DES.encrypt(str.getBytes(), password);

		System.out.println("���ܺ�" + toHexStringNew(result));

		String base64 = Base64.encode(result);
		System.out.println("���ܺ�" + base64);

		// ֱ�ӽ��������ݽ���
		try {
			byte[] decryResult = DES.decrypt(result, password);
			System.out.println("���ܺ�" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * ����
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɼ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// ���ڣ���ȡ���ݲ�����
			// ��ʽִ�м��ܲ���
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom random = new SecureRandom();
		// ����һ��DESKeySpec����
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// ����һ���ܳ׹���
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// ��DESKeySpec����ת����SecretKey����
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// ������ʼ���ܲ���
		return cipher.doFinal(src);
	}
}