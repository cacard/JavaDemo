/**
 * ThreadLocal Demo
 * 1 ThreadLocal�������������߳��в�����null����wrap�Ķ�����Ҫ��ʼ������ָ��initialValue������Ϊÿ���߳�����ʼ����
 * 2 [TODO]��û���Ķ�ThreadLocalԴ�������£�����Լ�ʵ��һ��ThreadLocal�ࣿ
 * 3 ��ͬThread֮���ThreadLocal�Ľ���
 */

package com.cacard.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalDemo {

	public static void main(String[] args) {
		// test1();
		testRef();
	}

	/**
	 * ThreadLocal�ĳ�ʼ��������ʱ�ĳ�ʼ�����Լ�����wrap�Ķ���ĳ�ʼ����
	 */
	static void test1() {
		final XX c = new XX();

		// main thread
		System.out.println(c.tl.get().i);

		// new thread
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println(c.tl.get().i);

			}
		}).start();
	}

	/**
	 * �̱߳��ر���Ҳ����ͨ������������
	 */
	static void testRef() {
		final XX c = new XX();

		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new Callable<Integer>() {
				@Override
				public Integer call() {
					return c.tl.get().i;
				}
			});
		}

		try {
			List<Future<Integer>> l = Executors.newFixedThreadPool(2).invokeAll(tasks);
			for (int i = 0; i < 10; i++) {
				System.out.println(l.get(i).get());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(c.refList);
		}

	}

}

/**
 * ����ThreadLocal�ֶε���
 */
class XX {
	public int j; // ���̱߳��ر���
	public ArrayList<YY> refList = new ArrayList<YY>(); // ���治ͬ�߳�֮��ThreadLocal<YY>������
	public ThreadLocal<YY> tl; // �̱߳��ر���
	public ThreadLocal<Integer> tl2;

	public XX() {
		// ֻ�����̳߳�ʼ��
		// tl=new ThreadLocal<ForThreadLocalGeneric>();
		// tl.set(new ForThreadLocalGeneric());

		// ÿ���߳̾���˳�ʼ��
		tl = new ThreadLocal<YY>() {
			@Override
			protected YY initialValue() {
				YY f = new YY();

				refList.add(f); // �ѵ�ǰ�̵߳ı��ر��������Ķ�����ӵ�refList

				f.i = 100;
				return f;
			}
		};

		tl2 = new ThreadLocal<Integer>();
	}

	public void SomeMethod() {
		System.out.println("[SomeMethod][threadid:" + Thread.currentThread().getId() + "]" + tl.get().i);
	}

}

/**
 * ThreadLocal��������
 */
class YY {
	public int i;

	public YY() {
		System.out.println("[ForThreadLocalGeneric ctor][threadid:" + Thread.currentThread().getId() + "]");
	}
}
