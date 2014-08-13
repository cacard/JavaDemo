/**
 * HashCode
 */

package com.cacard.javalang;

public class HashCodeDemo {

	public static void main(String[] args)
	{
		Integer i = Integer.MAX_VALUE;
		System.out.println(i+","+i.hashCode());
		
		Float f = 1f;
		System.out.println(f.hashCode());
	}
	
}
