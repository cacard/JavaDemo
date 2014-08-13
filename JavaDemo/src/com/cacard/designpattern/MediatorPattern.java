/**
 * 中介者模式
 * 
 * 族类中的每个类均包含中介者对象，而中介者包含了当前族类实例和它要交互的族类实例。
 * 实现的设计原则：松耦合/依赖于接口而不是具体实现
 * 
 * 角色：
 * 中介接口
 * 中介实现
 * 族类接口
 * 族类实现
 * Client
 * 
 * 核心思路：
 * 两个同学需要发送消息，为了不相互依赖，需要找一个中介。
 * 每个同学依赖于中介接口。
 * 中介实现了中介接口，并包含了两个同学的接口。
 * 发送消息时，同学A调用中介接口的sendMessage(消息内容,自身对象以区分发送给谁)。
 * 中介接口通过发送方识别出要发给的同学，并调用该同学的接收消息接口。
 * 
 */

package com.cacard.designpattern;

public class MediatorPattern {

	// Client
	public static void main(String[] args) {
		MediatorImpl mediator = new MediatorImpl();
		ClassMate01 mate1 = new ClassMate01(mediator);
		ClassMate02 mate2 = new ClassMate02(mediator);
		mediator.mate1 = mate1;
		mediator.mate2 = mate2;

		mediator.sendMessage("Hello from mate1", mate1);
		mediator.sendMessage("Hello from mate2", mate2);
	}

}

/** 中介者接口，供族类调用 */
interface IMediator {
	void sendMessage(String msg, IIClassMate from);
}

/** 中介者实现 */
class MediatorImpl implements IMediator {

	// 交互的2个族类
	public IIClassMate mate1; // 为了方便演示，采用了public
	public IIClassMate mate2;

	@Override
	public void sendMessage(String msg, IIClassMate from) {
		if (from == mate1) {
			mate2.notifyMessage(msg);
		} else {
			mate1.notifyMessage(msg);
		}
	}
}

/** 族类接口 */
interface IIClassMate {
	void notifyMessage(String msg);
}

/** 族类基类 */
class ClassMateBase {
	private IMediator mediator;

	public ClassMateBase(IMediator m) {
		this.mediator = m;
	}
}

/** 族类实现1 */
class ClassMate01 extends ClassMateBase implements IIClassMate {

	public ClassMate01(IMediator m) {
		super(m);
	}

	public void notifyMessage(String msg) {
		System.out.println("ClassMate1 get a message:" + msg);
	}
}

/** 族类实现2 */
class ClassMate02 extends ClassMateBase implements IIClassMate {

	public ClassMate02(IMediator m) {
		super(m);
	}

	public void notifyMessage(String msg) {
		System.out.println("ClassMate2 get a message:" + msg);
	}
}
