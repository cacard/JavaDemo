/**
 * 		幽灵引用/虚幻引用
 * 
 * 		- 用来跟踪对象何时彻底从内存中被清除。
 * 		- 准确来说是配合ReferenceQueue，PhantomReference单独使用无意义。
 * 
 * 		注意
 * 		如果对象有自己的finalize()，则该对象的回收过程比较特殊。第一次GC，被标记为可回收，因为有finalize()，所以进入F-Queue，等待Finalizer线程执行finalize()，完毕后，再GC一次。
 * 
 */

package com.cacard.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class Ref_PhantomReference {

	// 测试内部类对象
	private static void test() {
		ReferenceQueue<CanFinalize> q = new ReferenceQueue<CanFinalize>();
		PhantomReference<CanFinalize> pr = new PhantomReference<CanFinalize>(new Ref_PhantomReference().new CanFinalize(), q); // 以下代码用不到pr
		
		if(q.poll()==null){
			System.out.println("还未释放。");
		}else{
			System.out.println("已经析构，并且已经彻底释放");
			return;
		}
		
		System.gc(); // 尝试让GC释放对象
		
		try {
			System.out.println("->before");
			System.gc();
			Thread.sleep(1000);
			System.gc(); 
			Reference<? extends CanFinalize> r = q.remove(); //阻塞等待，等到后，从queue移除，返回。
			//Reference<? extends CanFinalize> r = q.poll(); // 不阻塞，查询有无，有则移除，返回。无则返回null。
			System.out.println("->after, and r:" + r); // 该语句之后，可确信对象已经完全从内存中清除
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		test();
	}

	class CanFinalize {

		private int _1M = 1024 * 1024;
		private byte[] b = new byte[_1M * 100]; // 用来让GC即时清除

		@Override
		public void finalize() throws Throwable {
			super.finalize(); // 这个必须，不然释放失败
			System.out.println("~()");
		}
	}

}
