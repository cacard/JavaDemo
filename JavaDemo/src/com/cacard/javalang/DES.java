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
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
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

	// 测试
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
			// 创建Cipher对象
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			// 加解密
			byte[] b = cipher.doFinal(source.getBytes("UTF8"));

			System.out.println("加密后：" + toHexStringNew(b));
			String base64 = Base64.encode(b);
			System.out.println("加密后：" + base64);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (1 == 1) {
			return;
		}

		// 待加密内容
		String str = "hello";
		// 密码，长度要是8的倍数
		String password = "atXW1224";

		byte[] result = DES.encrypt(str.getBytes(), password);

		System.out.println("加密后：" + toHexStringNew(result));

		String base64 = Base64.encode(result);
		System.out.println("加密后：" + base64);

		// 直接将如上内容解密
		try {
			byte[] decryResult = DES.decrypt(result, password);
			System.out.println("解密后：" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 加密
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
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}