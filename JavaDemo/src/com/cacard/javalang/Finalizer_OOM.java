/**
 * 
 * 		finalize()������OOM����
 * 
 * 		- Finalizer�߳��ǵ����ȼ��ģ�finalize()�Ĺ��̸����϶��󴴽��Ĺ��̡�
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

