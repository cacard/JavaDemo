/**
 * ArrayList��ز���
 * 
 * 1 ArrayList�ڲ�ԭ��
 * 2 ���ʵ���̰߳�ȫ��ArrayList
 * 3 ���������̰߳�ȫ��ʲô��fail-fast
 */

package com.cacard.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		// testArrayListProblems();
		// testThreadSafe();
		testIterator();
	}

	/**
	 * ���̰߳�ȫ��ArrayList����ʲô���⣿
	 */
	static void testArrayListProblems() {
		final ArrayList<String> list = new ArrayList<String>(); // ���̹߳����ArrayList

		// ����߳�ͬʱ����д����
		// ���� java.lang.ArrayIndexOutOfBoundsException
		// ԭ���ǣ����߳�1�ж�capacity�������Բ������ݺ󣬵���������ʱ֮ǰ���ѱ�����Thread����Ԫ�ء������������Χ�����쳣��
		int count = 10;
		Thread[] threads = new Thread[count];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						list.add("hello");
					}

				}
			});
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all joined.");

	}

	/**
	 * ArrayList���̰߳�ȫʵ��
	 */
	private static void testThreadSafe() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		// #1 �ֶ�ʵ��
		for (int i = 0; i < 10; i++) {
			synchronized (list) {
				list.add(i);
			}
		}

		// #2 ͬ����װ��
		List<Integer> syncList = Collections.synchronizedList(list);
		syncList.add(1);

		// #3 ʹ��J.U.C��������ݽṹ
		final CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<Integer>();
		int count = 10;
		Thread[] threads = new Thread[count];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						cowList.add(j);
						System.out.println("->" + j);
					}

				}
			});
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all joined.");

	}

	/**
	 * ����������
	 * �����ǡ����̰߳�ȫ���ģ��ڲ�ͨ��fail-fast����������Ƿ��ڵ��������з����޸ġ������ǵ��߳��»��߶��߳��£�����ʱ���޸ľ��׳��쳣
	 * ���߳��£�����ʱͨ��list.remove()�޸ģ������쳣������ͨ��iter.remove()�޸�
	 * ���߳��£�ÿ���߳�ͨ��iter.remove()Ҳ���������쳣
	 */
	private static void testIterator() {

		// ���̵߳���ʱ��list.�����������쳣
		final List<String> list = create(10);
		Iterator<String> iter = list.iterator();

		int j = 0;
		while (iter.hasNext()) {
			System.out.println(iter.next());
			if (j == 3) {
				list.add("11"); // ConcurrentModificationException
				// iter.remove();
			}
			j++;
		}
		print(list);

		// ���߳��£���ε�����ʹ��iter.remove()�����쳣
		final List<String> list2 = create(100);
		int count = 10;
		Thread[] threads = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					// ÿ���̵߳�������ͨ��iter�޸�
					int j = 0;
					for (Iterator<String> iter = list2.iterator(); iter
							.hasNext(); j++) {
						System.out.println(iter.next());
						if (j == 10) {
							iter.remove();
						}
					}

				}
			});
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all joined.");

	}

	/**
	 * ����һ��ArrayList
	 */
	private static List create(int count) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}

	/**
	 * ��ӡ
	 */
	private static void print(List<String> list) {
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}

}
