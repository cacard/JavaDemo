package com.cacard.ws;

import javax.xml.ws.Endpoint;

public class HelloServer {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/WS/Hello", new Hello());
	}
}
