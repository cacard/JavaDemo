package com.cacard.android_unused_resources_cleaner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Android������Դ������
 * 
 * ʹ�÷�����
 * 1��ʹ��Android Studio���õ�Inspect Code������ѰLint���ֵ�unused resource
 * 2�����������һ�����һ��AndroidLintUnusedResources.xml
 * 3���ڱ����������ú�AndroidLintUnusedResources.xml��·����AndroidProject��Ŀ¼·��
 * 4��ִ�иù���
 * 5���ظ��������̼��Ρ�������layout��drawable xml��ͼƬ��Դ�����ã�����ʹ�ñ�����ɾ��һЩ��Դ�󣬿��ٴ�lint���������Դ����������
 * 
 * ע��Ŀǰ��ɾ��
 * - res/drawable***
 * - res/layout
 * 
 * @author cunqingli
 */
public class App {

	// lint������AndroidLintUnusedResources.xmlλ��
	private static final String xmlPath = "C:\\2\\AndroidLintUnusedResources.xml";
	
	// android ��Ŀ�ĸ�Ŀ¼
	private static final String projectDir =  "C:\\Code\\tencent\\branches\\dev\\TencentNews_v4.7.9_final_small_package";
	
	public static void main(String[] args) {
		
//		if (args == null || args.length <= 1) {
//			System.out.println("error,please input the xml path.");
//			return;
//		}
//		
//		String xmlPath = args[1];
//		if (xmlPath == null || xmlPath.length() == 0) {
//			System.out.println("error,please input the xml path.");
//			return;
//		}
		
		SAXParser parser = null;
		InputStream is = null;
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			MyHandler parseHandler = new MyHandler();
			is = new FileInputStream(xmlPath);
			parser.parse(is, parseHandler);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					// 
				}
			}
		}
	}
	
	
	private static class MyHandler extends DefaultHandler {
		
		private boolean isFile = false;
		
		private void log(String msg) {
			System.out.println(msg);
		}
		
		public void startDocument() throws SAXException {
			super.startDocument();
			log("start");
		}
		
		public void endDocument() throws SAXException {
			log("end");
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("file")){
				isFile = true;
			} else {
				isFile = false;
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName)throws SAXException {
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(isFile){
				String s = new String(ch, start, length);
				//log("find a <file>:" + s);
				delete(s);
			}
		}
		
		// file��e.g.��file://$PROJECT_DIR$/res/drawable-xhdpi/rss_rose_before.png
		private void delete(String file) {
			if (file == null || file.length() == 0) {
				return;
			}
			
			if (!file.contains("/res/drawable") 
					//&& !file.contains("/res/layout/")
					) {
				return;
			}
			
			String AppRootTag = "$PROJECT_DIR$";
			
			file = file.replace("file://", "");
			file = file.replace("/", "\\");
			file = file.replace("$PROJECT_DIR$", projectDir);
			
			try {
				File f = new File(file);
				if (!f.exists()) {
					System.out.println("[error] not exit:"+file);
				} else {
					f.delete();
					System.out.println("[ok] delete success:"+file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
