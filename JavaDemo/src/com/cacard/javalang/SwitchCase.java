package com.cacard.javalang;

public class SwitchCase {

	public static void main(String[] args) {
		int a = 1;

		switch (a) {
		case 1:
			System.out.println("1");
		case 2:
			System.out.println("2");
			return;
		default:
			System.out.println("default");
			break;
		}
	}

}
