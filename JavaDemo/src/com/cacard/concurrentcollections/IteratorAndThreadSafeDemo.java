/**
 * �������̰߳�ȫ
 * 
 * �̰߳�ȫ/���̰߳�ȫ���ϵĵ������̾��Ƿ��̰߳�ȫ�ģ�����ѵ������̺ͶԼ��ϵ��޸Ĺ��̽���ͬ����
 * 
 */

package com.cacard.concurrentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IteratorAndThreadSafeDemo {

	public static void main(String[] args) {
		testIter();
	}

	private static void testIter() {
		final List<Object> syncList = Collections
				.synchronizedList(new ArrayList<Object>());
		for (int i = 0; i < 10; i++) {
			syncList.add(new Object());
		}

		int j = 0;

		synchronized (syncList) {

			for (Iterator<Object> iter = syncList.iterator(); iter.hasNext();)
			// for(Object o : syncList)
			{
				System.out.println(iter.next());
				// System.out.println(o);

				if (j == 3) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							syncList.add(new Object());
						}
					}).start();

				}

				j++;
			}
		}

	}

}
