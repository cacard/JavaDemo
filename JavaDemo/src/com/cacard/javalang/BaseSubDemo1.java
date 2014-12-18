package com.cacard.javalang;

public class BaseSubDemo1 {

}

interface IResponse {
	void m();
}

class ResponseCommon implements IResponse {

	IResponse b;

	public ResponseCommon(IResponse b) {
		this.b = b;
	}

	public void m() {
		b.m();
	};
}

class BaseBase implements IResponse {
	public void m() {
		System.out.println("BaseBase");
	}
}

class SubSub extends BaseBase implements IResponse {
	public void m() {
		System.out.println("SubSub");
	}
}