/**
 * 		Ref 引用
 * 			- java.lang.ref
 * 			- 4种
 * 				* Strong
 * 				* Soft
 * 				* Weak
 * 				* Phantom幽灵
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

		// GC测试
		System.out.println(softRef.get() == null);	// false
		System.gc();
		System.out.println(softRef.get() == null);	// false
	}
	
	// WeakReference
	static void testWeakReference() {
		WeakReference<List<Integer>> weakRef = new WeakReference<List<Integer>>(new ArrayList<Integer>());

		// GC测试
		System.out.println(weakRef.get() == null); // GC之前:false
		System.gc();							   // System.gc()并不一定真的发生一次full GC
		System.out.println(weakRef.get() == null); // GC之后:true
	}

	public static void main(String[] args) {
		testSoftReference();
		
		for(int i=0;i<100;i++)
			testWeakReference();
	}

}
