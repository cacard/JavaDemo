/**
 		WeakHashMap

 * key��weak key��
 * ��key����ʹ��ʱ�����ⲻ����ĳ��ʱ�̱�GC�ռ��ˣ���entry���Զ���ȥ����
 * ����value��ǿ���ã�����Ӧ����valueֱ�ӻ�������key��
 * ��ʱȥ��Entry��
 * �ڲ�ͨ������ expungeStaleEntries() ����� key �Ƿ��ͷţ�Ȼ��ȥ����Ӧ�� Entry��
 * ���� expungeStaleEntries() �ķ�����
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

	// ��ʱ�ͷ�Entry?
	private static void test() {
		WeakHashMap<Integer, String> map = new WeakHashMap<Integer, String>();

		// add elements
		for (int i = 0; i < 10; i++) {
			Integer iObj = new Integer(i); // �뿪forѭ���飬iObj���ͷ�
			map.put(iObj, String.valueOf(i));
		}

		System.gc();// ����һ��GC

		// ����ʱ�������ͷ�Entry����Ϊû�ж�map����һЩ��������Щ���������expungeStaleEntries()���ͷ�entry��
		print(map);// ����WeakHashMapʱ�ͷ�Entry

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
