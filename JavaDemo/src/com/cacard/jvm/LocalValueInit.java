package com.cacard.jvm;

public class LocalValueInit {

	public static void main(String[] args) {
		int a = 0;
		if (1 != 2 && (a = 2) == 2) {
			System.out.println("yes");
		}
	}

	public void init() {
		int a = new T().m;
	}
	
	public static class T {
		private int m;
	}
}
