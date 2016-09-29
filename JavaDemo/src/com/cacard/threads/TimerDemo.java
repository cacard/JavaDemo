package com.cacard.threads;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Demo of Timer
 * 
 * @author cunqingli
 */
public class TimerDemo {

	public static void main(String[] args) {
		problem1();
	}

	/**
	 * ʱ������
	 * ����2��������1ִ����Ϻ�ſ�ʼ����ʱ�ӣ�
	 */
	private static void problem1() {
		Timer timer = new Timer();

		TimerTask r1 = getTask(5000, "t1");
		// ����1����ʱ5�룬�ӳ�1��
		timer.schedule(r1, 1000);

		// ����2����ʱ�̣ܶ��ӳ�1��
		TimerTask r2 = getTask(0, "t2");
		timer.schedule(r2, 1000);

		// �����
		// ����2��ʵ��ִ�м����t1�ļ����
	}

	private static TimerTask getTask(final long sleep, final String taskName) {
		return new TimerTask() {
			@Override
			public void run() {
				System.out.println(taskName + "@" + System.currentTimeMillis());
				try {
					Thread.sleep(sleep);
				} catch (Exception e) {
				}
			}
		};
	}

}
