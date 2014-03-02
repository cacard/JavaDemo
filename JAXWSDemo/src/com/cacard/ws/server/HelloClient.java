package com.cacard.ws.server;

import java.rmi.RemoteException;

import javax.naming.NamingException;

public class HelloClient {

	public static void main(String[] args) throws NamingException,RemoteException
	{
		HelloService s = new HelloService();
		Hello port = s.getPort(Hello.class); // ´úÀí
		
		String result = port.sayHello("world");
		System.out.println(result);
	}
	
}
