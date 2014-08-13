/**
 * NewtorkInterface/InetAddress
 * 
 * 
 */

package com.cacard.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkBasic {

	public static void main(String[] args) {

	}

	/**
	 * NetworkInterface
	 */
	public static void testNetworkInterface() {
		try {
			NetworkInterface fa = NetworkInterface.getByName("eth0");
			System.out.println(fa.getDisplayName());
			System.out.println("_______");

			Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
			while (faces.hasMoreElements()) {
				System.out.println(faces.nextElement().getDisplayName());
			}

		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * InetAddress
	 */
	public static void testInetAddress() {
		try {
			InetAddress[] adds = InetAddress.getAllByName("localhost");
			for (InetAddress a : adds) {
				System.out.println(a.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		try {
			InetAddress addr = InetAddress.getByName("www.diglog.com");
			boolean b = addr.isReachable(5000);
			System.out.println(b);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
