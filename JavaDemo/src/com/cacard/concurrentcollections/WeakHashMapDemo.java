/**
 		WeakHashMap

 * key是weak key。
 * 当key不再使用时（即免不了在某个时刻被GC收集了），entry会自动被去除。
 * 由于value是强引用，所以应避免value直接或间接引用key。
 * 何时去除Entry？
 * 内部通过函数 expungeStaleEntries() 来检查 key 是否被释放，然后去除对应的 Entry。
 * 调用 expungeStaleEntries() 的方法：
 * size()
 * resize()
 * get()
 * getEntry()
 * put()
 * remove()
 * removeMapping()
 * containsXXX()

 */

package com.cacard.concurrentcollections;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

	// 何时释放Entry?
	private static void test() {
		WeakHashMap<Integer, String> map = new WeakHashMap<Integer, String>();

		// add elements
		for (int i = 0; i < 10; i++) {
			Integer iObj = new Integer(i); // 离开for循环块，iObj会释放
			map.put(iObj, String.valueOf(i));
		}

		System.gc();// 并不一定GC

		// 此行时，并不释放Entry，因为没有对map进行一些操作（这些操作会调用expungeStaleEntries()以释放entry）
		print(map);// 访问WeakHashMap时释放Entry

	}

	private static void print(Map<?, ?> map) {
		if (map == null || map.size() == 0) {
			System.out.println("null/size==0");
			return;
		}

		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (entry == null) {
				System.out.println("entry is null");
			} else {
				System.out.println("->" + entry.getKey() + ","
						+ entry.getValue());
			}
		}
	}

	public static void main(String[] args) {
		test();
	}

}
