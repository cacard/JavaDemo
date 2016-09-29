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
	 * 时延问题
	 * 任务2会在任务1执行完毕后才开始计算时延！
	 */
	private static void problem1() {
		Timer timer = new Timer();

		TimerTask r1 = getTask(5000, "t1");
		// 任务1，耗时5秒，延迟1秒
		timer.schedule(r1, 1000);

		// 任务2，耗时很短，延迟1秒
		TimerTask r2 = getTask(0, "t2");
		timer.schedule(r2, 1000);

		// 结果：
		// 任务2的实际执行间隔是t1的间隔！
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
