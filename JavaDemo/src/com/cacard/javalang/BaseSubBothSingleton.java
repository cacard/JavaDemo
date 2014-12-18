package com.cacard.javalang;

public class BaseSubBothSingleton {
	
	public static void main(String[] args){
		Sub.getInstance().m();
	}

	static class Base{
		private Base(){
			
		}
	}
	
	static class Sub extends Base {
		private static Sub sub;
		
		private Sub(){
			
		}
		
		public static Sub getInstance () {
			if (sub == null) {
				sub = new Sub();
			}
			return sub;
		}
		
		void m(){
			
		}
	}
	
}
