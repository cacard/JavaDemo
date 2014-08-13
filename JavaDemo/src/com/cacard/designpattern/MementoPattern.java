/**
 * 备忘录模式/快照模式
 * Originator/Memento
 */

package com.cacard.designpattern;

public class MementoPattern {

	public static void main(String[] args) {
		Originator o = new Originator(1);
		Memento m = o.saveSate();
		o.setState(2);
		o.restoreFromMemento(m);
		System.out.println(o.getState());
	}

}

/* Originator */
class Originator {
	private int state;

	public Originator(int state) {
		this.setState(state);
	}

	/* 保存状态到备忘录 */
	public Memento saveSate() {
		return new Memento(this.getState());
	}

	/* 从备忘录恢复 */
	public void restoreFromMemento(Memento m) {
		this.setState(m.getState());
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}

/* Memento */
class Memento {
	private int state;

	public Memento(int s) {
		this.state = s;
	}

	public int getState() {
		return this.state;
	}
}