/**
 * 哪些情况下锁会自动释放？
 * 异常?中断?sleep?
 */

package com.cacard.javademo;

public class LockRelease {

	public static void main(String[] args)
	{
		
	}
	
	synchronized void syncMethod()
	{
//		 1 同步方法执行完毕后，内部锁会自动释放。
//		 2 出现异常，内部锁会自动释放。
//		 3 wait时，释放锁。等待信号。
//		 4 sleep时，放弃CPU调度，休眠，不释放锁。
//		 5 被 interrupted 时，如果抛出异常，等同于2
	}
	
}
