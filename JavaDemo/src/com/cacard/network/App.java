package com.cacard.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class App {

	public static void main(String[] args) {
		testUrlUri();
	}
	
	/**
	 * URL URI
	 */
	public static void testUrlUri()
	{
		
	}
	
	/**
	 * NetworkInterface
	 */
	public static void testNetworkInterface()
	{
		try {
			
			NetworkInterface fa = NetworkInterface.getByName("eth0");
			System.out.println(fa.getDisplayName());
			
			System.out.println("_______");
			
			Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
			while(faces.hasMoreElements())
			{
				System.out.println(faces.nextElement().getDisplayName());
			}
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * InetAddress
	 */
	public static void testInetAddress() {
		try {
			InetAddress[] add = InetAddress.getAllByName("localhost");

			for (InetAddress a : add) {
				System.out.println(a.getHostAddress());

			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			InetAddress addr = InetAddress.getByName("www.diglog.com");

			boolean b = addr.isReachable(5000);

			System.out.println(b);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
