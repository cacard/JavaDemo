/**
 * 		BlockingQueue������BQ�Ľӿ�
 * 
 * 		������4��
 * 			- �����쳣�������������쳣
 * 			- ͨ������ֵ������״�����������˷���false
 * 			- ����:��������������ǰ�߳�
 * 			- ��ʱ���������˵ȴ�һ��ʱ�䣬������������������ͷ�����
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
