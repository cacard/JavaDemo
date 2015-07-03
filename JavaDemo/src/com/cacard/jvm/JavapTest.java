package com.cacard.jvm;

public class JavapTest {

	void spin() {
		int i;
		for (i = 0; i < 100; i++) {
			; // loop
		}
	}

	int invokeMethod() {
		return add(1, 2);
	}

	int add(int a, int b) {
		return a + b;
	}

	void createInstance() {
		JavapTest obj = new JavapTest();
	}

}
