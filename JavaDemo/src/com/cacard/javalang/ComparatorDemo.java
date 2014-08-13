/**
 * 自定义比较器
 */

package com.cacard.javalang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemo {

	public static void main(String[] args) {

		List<BoolCompare> l = new ArrayList<BoolCompare>();
		l.add(new BoolCompare(false));
		l.add(new BoolCompare(false));
		l.add(new BoolCompare(true));

		Collections.sort(l, new Comparator<BoolCompare>() {

			@Override
			public int compare(BoolCompare arg0, BoolCompare arg1) {
				if (arg0.isV() && arg1.isV() == false) {
					return -1;
				}
				return 0;
			}
		});

		for (BoolCompare b : l) {
			System.out.println(b.isV());
		}
	}

	public static class BoolCompare {
		private boolean v;

		public BoolCompare(boolean b) {
			this.v = b;
		}

		public boolean isV() {
			return v;
		}

		public void setV(boolean v) {
			this.v = v;
		}
	}

}
