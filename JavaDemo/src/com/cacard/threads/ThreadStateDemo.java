/**
 * 
 * 线程状态
 * 
 * 重点概念：可运行/阻塞(被动)/等待(主动)
 * 
 * Linux下的线程状态有:
 * 1 等待运行（等待分配CPU）
 * 2 正在运行
 * 3 睡眠（睡眠分为可接收信号和不接收信号两种）
 * 
 * Java的线程状态有：
 * 1 新创建
 * 2 可运行（等待内核分配CPU时间）
 * 3 正运行
 * 4 终结
 * 5 Blocked 阻塞（被动状态，比如获取锁没有获取成功）
 * 6 Waiting 等待（主动状态，比如wait()，等待一个信号）
 * 
 */




package com.cacard.threads;

public class ThreadStateDemo {

}
