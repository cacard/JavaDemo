package com.cacard.javalang;

import java.util.ArrayList;

public class CloneDemo {

	public static void main(String[] args){
		ClassCanClone c = new ClassCanClone();
		c.count=1;
		
		ClassCanClone cc = (ClassCanClone)c.clone();
		
		System.out.println(c==cc);
		System.out.println(cc.count);
	}
	
	public static class ClassCanClone implements Cloneable/*��־�ӿ�*/{
		private int count;
		
		/**
		 * clone()ʵ�ַ�ʽ
		 */
		public ClassCanClone clone(){
			ClassCanClone o=null;
			try{
				o=(ClassCanClone)super.clone();
			}catch(CloneNotSupportedException e){
				// pass
			}
			return o;
		}
	}
}
