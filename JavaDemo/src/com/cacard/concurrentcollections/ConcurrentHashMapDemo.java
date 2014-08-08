/*
 * 		ConcurrentHashMap
 * 
 * 
 * 
 */

package com.cacard.concurrentcollections;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

	public static void main(String[] args) {
		testIterator();
	}

	static void testIterator() {
		final ConcurrentHashMap<String, String> cHashMap = new ConcurrentHashMap<>();

		// add elements
		for (int i = 0; i < 10; i++) {
			cHashMap.put(String.valueOf(i), String.valueOf(i));
		}

		final Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 999; i++) {
					cHashMap.put(String.valueOf(i) + "_", "__");
				}
			}
		});

		// iter
		for (Iterator<Entry<String, String>> iter = cHashMap.entrySet()
				.iterator(); iter.hasNext();) {

			Entry<String, String> item = iter.next();
			if (item.getKey().equals("1")) {
				t.start();
			}

			System.out.println(iter.next().toString());
		}
		
		print(cHashMap);
	}

	static void print(ConcurrentHashMap<String, String> map) {
		// iter
		for (Iterator<Entry<String, String>> iter = map.entrySet().iterator(); iter
				.hasNext();) {

			System.out.println(iter.next().toString());
		}

	}

}
