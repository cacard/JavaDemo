/**
 * �򵥵Ĺ۲���ģʽ
 * 
 * 		- ������
 * 			���������߶����������������һ���ﵽ100���ʹ����۲��߶����ĳ��������
 * 
 * 		- Ҫ���֡��۲��ߡ��͡����۲��ߡ���
 */

package com.cacard.designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// �����۲��ߵ�������Լ̳��Ըýӿڣ��¼�����ʱ�Ļص��������Ʊ��̶�������
interface CanAsWather {
	void fire();
}

public class Observer1_Simple {

	// �۲���
	public static class Watcher implements CanAsWather {
		public void fire() {
			System.out.println("�۲쵽���¼��ķ���");
		}
	}

	// ���۲�ļ����������۲��ߣ�
	public static class Counter {
		private AtomicInteger counter = new AtomicInteger(0);
		private CanAsWather watcher; // �����ڽӿڣ����������ھ������

		public Counter(CanAsWather w) { // ���캯������ע��
			this.watcher = w;
		}

		public void start() {
			while (counter.get() < 100) {
				counter.getAndIncrement();
			}
			watcher.fire(); // �����¼�
		}
	}

	// �Ľ������۲��ߡ���JDK�еı��۲���ʵ���˿ɹ�������۲���
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
