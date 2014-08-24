/**
 * 	可finalize()的类
 * 
 */

package com.cacard.javalang;

import java.util.concurrent.atomic.AtomicInteger;

public class FinalizableClass {
	public static AtomicInteger liveCount = new AtomicInteger(0); // 用来计算某个时刻没有被finalize()的对象数量

	public FinalizableClass() {
		liveCount.getAndIncrement(); // 创建实例时，计数器++
	}

	@Override
	protected void finalize() {
		liveCount.getAndDecrement(); // 被finalize()后，计数器--
	}

}
