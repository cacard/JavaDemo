/**
 * 
 * 		finalize()引发的OOM问题
 * 
 * 		- Finalizer线程是低优先级的，finalize()的过程跟不上对象创建的过程。
 */

package com.cacard.javalang;

public class Finalizer_OOM {

	public static void main(String[] args){
		while(true){
			FinalizableClass obj = new FinalizableClass();
			System.out.println("->"+obj.liveCount);
		}
	}
	
}

