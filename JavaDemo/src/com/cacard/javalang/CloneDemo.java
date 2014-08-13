/**
 * 深度克隆
 */

package com.cacard.javalang;

public class CloneDemo {

	public static void main(String[] args) {
		ClassCanClone c = new ClassCanClone();
		c.count = 1;

		ClassCanClone cc = (ClassCanClone) c.clone();

		System.out.println(c == cc);
		System.out.println(cc.count);
	}

	public static class ClassCanClone implements Cloneable/* 标志接口 */{
		private int count;

		/**
		 * clone()实现方式
		 */
		public ClassCanClone clone() {
			ClassCanClone o = null;
			try {
				o = (ClassCanClone) super.clone();
			} catch (CloneNotSupportedException e) {
				// pass
			}
			return o;
		}
	}
}
