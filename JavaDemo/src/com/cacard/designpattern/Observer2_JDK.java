/**
 * JDK�еĹ۲���ģʽ
 * 	- ���۲��߿�ע�����۲���
 * 
 */

package com.cacard.designpattern;

import java.util.Observable;
import java.util.Observer;

public class Observer2_JDK {
	public static void main(String[] args) {
		Observer1 observer1 = new Observer1();
		Observer2 observer2 = new Observer2();
		Observable1 obj = new Observable1();
		obj.addObserver(observer1);
		obj.addObserver(observer2);
		obj.change();
	}
}

/** �۲���1 */
class Observer1 implements Observer {
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Observer1:find something changed...");
	}
}

/** �۲���2 */
class Observer2 implements Observer {
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Observer2:find something changed...");
	}
}

/** ���۲����1 */
class Observable1 extends Observable {
	public void change() {
		this.setChanged();
		this.notifyObservers();
	}
}

/** ���۲����2 */
class Observable2 extends Observable {
	public void change() {
		this.setChanged();
		this.notifyObservers();
	}
}