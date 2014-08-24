/**
 * 	��finalize()����
 * 
 */

package com.cacard.javalang;

import java.util.concurrent.atomic.AtomicInteger;

public class FinalizableClass {
	public static AtomicInteger liveCount = new AtomicInteger(0); // ��������ĳ��ʱ��û�б�finalize()�Ķ�������

	public FinalizableClass() {
		liveCount.getAndIncrement(); // ����ʵ��ʱ��������++
	}

	@Override
	protected void finalize() {
		liveCount.getAndDecrement(); // ��finalize()�󣬼�����--
	}

}
