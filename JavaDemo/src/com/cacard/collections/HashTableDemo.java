/**
 * Hashtable
 * 1 thread-safe
 * 2 Entry[] 作为槽，每个Entry可做链表解决冲突。
 * 3 Entry[] 剩余空间减少到一定量，进行 rehash。
 * 4 rehash 的过程就是重新计算每个Entry的槽位。
 * 5 key/value 不允许null。
 */

package com.cacard.collections;

import java.util.Hashtable;

public class HashTableDemo {

	public static void main(String[] args)
	{
		Hashtable<String,String> t = new Hashtable<>();
	}
}

