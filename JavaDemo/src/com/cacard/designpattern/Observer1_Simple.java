/**
 * 简单的观察者模式
 * 
 * 		- 场景：
 * 			两个（或者多个）计数器，任意一个达到100，就触发观察者对象的某个函数。
 * 
 * 		- 要区分“观察者”和“被观察者”。
 */

package com.cacard.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// 能做观察者的类均可以继承自该接口，事件发生时的回调函数名称被固定下来。
interface CanAsWather {
	void fire();
}

public class Observer1_Simple {

	// 观察者
	public static class Watcher implements CanAsWather {
		public void fire() {
			System.out.println("观察到了事件的发生");
		}
	}

	// 被观察的计数器（被观察者）
	public static class Counter {
		private AtomicInteger counter = new AtomicInteger(0);
		private CanAsWather watcher; // 依赖于接口，而不依赖于具体的类

		public Counter(CanAsWather w) { // 构造函数依赖注入
			this.watcher = w;
		}

		public void start() {
			while (counter.get() < 100) {
				counter.getAndIncrement();
			}
			watcher.fire(); // 触发事件
		}
	}

	// 改进“被观察者”。JDK中的被观察者实现了可关联多个观察者
	public static class Counter2 {
		private List<CanAsWather> list = new ArrayList<CanAsWather>();

		public void addObserver(CanAsWather o) {
			this.list.add(o);
		}

		public void start() {
			// ...
			eventHappen();
		}

		private void eventHappen() {
			for (CanAsWather item : list) {
				item.fire();
			}
		}
	}

	public static void main(String[] args) {
		CanAsWather w = new Watcher();
		Counter c1 = new Counter(w);
		Counter c2 = new Counter(w);

		c1.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		c2.start();

	}

}
