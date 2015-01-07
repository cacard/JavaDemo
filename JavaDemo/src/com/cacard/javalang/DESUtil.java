package com.cacard.javalang;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;
 
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class DESUtil {
 
    Key key ;
 
    public DESUtil() {
 
    }
 
    public DESUtil(String str) {
       setKey(str); // �����ܳ�
    }
 
    public Key getKey() {
       return key ;
    }
 
    public void setKey(Key key) {
       this . key = key;
    }
 
    /**
      * ���ݲ������� KEY
      */
    public void setKey(String strKey) {
       try {
           KeyGenerator _generator = KeyGenerator.getInstance ( "DES" );
           _generator.init( new SecureRandom(strKey.getBytes()));
           this . key = _generator.generateKey();
           _generator = null ;
       } catch (Exception e) {
           throw new RuntimeException(
                  "Error initializing SqlMap class. Cause: " + e);
       }
    }
 
    /**
      * ���� String �������� ,String �������
      */
    public String encryptStr(String strMing) {
       byte [] byteMi = null ;
       byte [] byteMing = null ;
       String strMi = "" ;
       BASE64Encoder base64en = new BASE64Encoder();
       try {
           byteMing = strMing.getBytes( "UTF8" );
           byteMi = this .encryptByte(byteMing);
           strMi = base64en.encode(byteMi);
       } catch (Exception e) {
           throw new RuntimeException(
                  "Error initializing SqlMap class. Cause: " + e);
       } finally {
           base64en = null ;
           byteMing = null ;
           byteMi = null ;
       }
       return strMi;
    }
 
    /**
      * ���� �� String �������� ,String �������
      *
      * @param strMi
      * @return
      */
    public String decryptStr(String strMi) {
       BASE64Decoder base64De = new BASE64Decoder();
       byte [] byteMing = null ;
       byte [] byteMi = null ;
       String strMing = "" ;
       try {
           byteMi = base64De.decodeBuffer(strMi);
           byteMing = this .decryptByte(byteMi);
           strMing = new String(byteMing, "UTF8" );
       } catch (Exception e) {
           throw new RuntimeException(
                  "Error initializing SqlMap class. Cause: " + e);
       } finally {
           base64De = null ;
           byteMing = null ;
           byteMi = null ;
       }
       return strMing;
    }
 
    /**
      * ������ byte[] �������� ,byte[] �������
      *
      * @param byteS
      * @return
      */
    private byte [] encryptByte( byte [] byteS) {
       byte [] byteFina = null ;
       Cipher cipher;
       try {
           cipher = Cipher.getInstance ( "DES" );
           cipher.init(Cipher. ENCRYPT_MODE , key );
           byteFina = cipher.doFinal(byteS);
       } catch (Exception e) {
           throw new RuntimeException(
                  "Error initializing SqlMap class. Cause: " + e);
       } finally {
           cipher = null ;
       }
       return byteFina;
    }
 
    /**
      * ������ byte[] �������� , �� byte[] �������
      *
      * @param byteD
      * @return
      */
    private byte [] decryptByte( byte [] byteD) {
       Cipher cipher;
       byte [] byteFina = null ;
       try {
           cipher = Cipher.getInstance ( "DES" );
           cipher.init(Cipher. DECRYPT_MODE , key );
           byteFina = cipher.doFinal(byteD);
       } catch (Exception e) {
           throw new RuntimeException(
                  "Error initializing SqlMap class. Cause: " + e);
       } finally {
           cipher = null ;
       }
       return byteFina;
    }
 
    /**
      * �ļ� file ���м��ܲ�����Ŀ���ļ� destFile ��
      *
      * @param file
      *             Ҫ���ܵ��ļ� �� c:/test/srcFile.txt
      * @param destFile
      *             ���ܺ��ŵ��ļ��� �� c:/ ���ܺ��ļ� .txt
      */
    public void encryptFile(String file, String destFile) throws Exception {
       Cipher cipher = Cipher.getInstance ( "DES" );
       // cipher.init(Cipher.ENCRYPT_MODE, getKey());
       cipher.init(Cipher. ENCRYPT_MODE , this . key );
       InputStream is = new FileInputStream(file);
       OutputStream out = new FileOutputStream(destFile);
       CipherInputStream cis = new CipherInputStream(is, cipher);
       byte [] buffer = new byte [1024];
       int r;
       while ((r = cis.read(buffer)) > 0) {
           out.write(buffer, 0, r);
       }
       cis.close();
       is.close();
       out.close();
    }
 
    /**
      * �ļ����� DES �㷨�����ļ�
      *
      * @param file
      *             �Ѽ��ܵ��ļ� �� c:/ ���ܺ��ļ� .txt *
      * @param destFile
      *             ���ܺ��ŵ��ļ��� �� c:/ test/ ���ܺ��ļ� .txt
      */
    public void decryptFile(String file, String dest) throws Exception {
       Cipher cipher = Cipher.getInstance ( "DES" );
       cipher.init(Cipher. DECRYPT_MODE , this . key );
       InputStream is = new FileInputStream(file);
       OutputStream out = new FileOutputStream(dest);
       CipherOutputStream cos = new CipherOutputStream(out, cipher);
       byte [] buffer = new byte [1024];
       int r;
       while ((r = is.read(buffer)) >= 0) {
           cos.write(buffer, 0, r);
       }
       cos.close();
       out.close();
       is.close();
    }
 
    public static void main(String[] args) throws Exception {
       DESUtil des = new DESUtil( "atXW1224" );
       // DES �����ļ�
       // des.encryptFile("G:/test.doc", "G:/ ���� test.doc");
       // DES �����ļ�
       // des.decryptFile("G:/ ���� test.doc", "G:/ ���� test.doc");
       String str1 = "hello" ;
       // DES �����ַ���
       String str2 = des.encryptStr(str1);
       // DES �����ַ���
       String deStr = des.decryptStr(str2);
       System. out .println( " ����ǰ�� " + str1);
       System. out .println( " ���ܺ� " + str2);
       System. out .println( " ���ܺ� " + deStr);
    }
} 