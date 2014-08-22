/**
 * 		BlockingQueue是所有BQ的接口
 * 
 * 		操作分4种
 * 			- 可抛异常：队列满了抛异常
 * 			- 通过返回值来表明状况：队列满了返回false
 * 			- 阻塞:队列满了阻塞当前线程
 * 			- 超时：队列满了等待一段时间，如果还不满足条件，就放弃。
 * 
 */

package com.cacard.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BQ_BlockingQueue {

	void bqDemo(){
		BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(10);
	}
	
	public static void main(String[] args){
		
	}
	
}
