package com.cacard.javalang;

public class TryCatchDemo {

	public static void main(String[] args) {
		try {
			int a = 1;
			int b = 0;
			int c = a / b;
		} catch (Exception e) {

		}

		finally {
			System.out.println("finally.");
		}

	}

}
