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
 * Android无用资源清理工具
 * 
 * 使用方法：
 * 1，使用Android Studio内置的Inspect Code工具搜寻Lint发现的unused resource
 * 2，导出结果，一般会有一个AndroidLintUnusedResources.xml
 * 3，在本程序中配置好AndroidLintUnusedResources.xml的路径和AndroidProject根目录路径
 * 4，执行该工具
 * 5，重复上述过程几次。（由于layout和drawable xml对图片资源有引用，所以使用本程序删除一些资源后，可再次lint检查无用资源并导出。）
 * 
 * 注：目前可删除
 * - res/drawable***
 * - res/layout
 * 
 * @author cunqingli
 */
public class App {

	// lint导出的AndroidLintUnusedResources.xml位置
	private static final String xmlPath = "C:\\2\\AndroidLintUnusedResources.xml";
	
	// android 项目的根目录
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
		
		// file，e.g.：file://$PROJECT_DIR$/res/drawable-xhdpi/rss_rose_before.png
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
