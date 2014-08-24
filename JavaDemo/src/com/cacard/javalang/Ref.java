/**
 * 		Ref ����
 * 			- java.lang.ref
 * 			- 4��
 * 				* Strong
 * 				* Soft
 * 				* Weak
 * 				* Phantom����
 * 
 * 
 */

package com.cacard.javalang;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Ref {

	// SoftReference
	static void testSoftReference() {
		SoftReference<List<Integer>> softRef = new SoftReference<List<Integer>>(new ArrayList<Integer>());

		// GC����
		System.out.println(softRef.get() == null);	// false
		System.gc();
		System.out.println(softRef.get() == null);	// false
	}
	
	// WeakReference
	static void testWeakReference() {
		WeakReference<List<Integer>> weakRef = new WeakReference<List<Integer>>(new ArrayList<Integer>());

		// GC����
		System.out.println(weakRef.get() == null); // GC֮ǰ:false
		System.gc();							   // System.gc()����һ����ķ���һ��full GC
		System.out.println(weakRef.get() == null); // GC֮��:true
	}

	public static void main(String[] args) {
		testSoftReference();
		
		for(int i=0;i<100;i++)
			testWeakReference();
	}

}
