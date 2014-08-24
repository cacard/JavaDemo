/**
 * 
 * 
 */
package com.cacard.javalang;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Ref_ReferenceQueue {

	private static void test() {
		ReferenceQueue<Integer> rq = new ReferenceQueue<Integer>();

		WeakReference<Integer> wr = new WeakReference<Integer>(new Integer(99999), rq);
		
		System.gc();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Reference<? extends Integer> r = rq.poll();
		System.out.println(r);
		if(r!=null)
			System.out.println(r.get()==null);

	}

	public static void main(String[] args) {
		test();
	}

}
