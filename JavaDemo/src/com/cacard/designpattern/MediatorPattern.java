/**
 * �н���ģʽ
 * 
 * �����е�ÿ����������н��߶��󣬶��н��߰����˵�ǰ����ʵ������Ҫ����������ʵ����
 * ʵ�ֵ����ԭ�������/�����ڽӿڶ����Ǿ���ʵ��
 * 
 * ��ɫ��
 * �н�ӿ�
 * �н�ʵ��
 * ����ӿ�
 * ����ʵ��
 * Client
 * 
 * ����˼·��
 * ����ͬѧ��Ҫ������Ϣ��Ϊ�˲��໥��������Ҫ��һ���н顣
 * ÿ��ͬѧ�������н�ӿڡ�
 * �н�ʵ�����н�ӿڣ�������������ͬѧ�Ľӿڡ�
 * ������Ϣʱ��ͬѧA�����н�ӿڵ�sendMessage(��Ϣ����,������������ַ��͸�˭)��
 * �н�ӿ�ͨ�����ͷ�ʶ���Ҫ������ͬѧ�������ø�ͬѧ�Ľ�����Ϣ�ӿڡ�
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

/** �н��߽ӿڣ���������� */
interface IMediator {
	void sendMessage(String msg, IIClassMate from);
}

/** �н���ʵ�� */
class MediatorImpl implements IMediator {

	// ������2������
	public IIClassMate mate1; // Ϊ�˷�����ʾ��������public
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

/** ����ӿ� */
interface IIClassMate {
	void notifyMessage(String msg);
}

/** ������� */
class ClassMateBase {
	private IMediator mediator;

	public ClassMateBase(IMediator m) {
		this.mediator = m;
	}
}

/** ����ʵ��1 */
class ClassMate01 extends ClassMateBase implements IIClassMate {

	public ClassMate01(IMediator m) {
		super(m);
	}

	public void notifyMessage(String msg) {
		System.out.println("ClassMate1 get a message:" + msg);
	}
}

/** ����ʵ��2 */
class ClassMate02 extends ClassMateBase implements IIClassMate {

	public ClassMate02(IMediator m) {
		super(m);
	}

	public void notifyMessage(String msg) {
		System.out.println("ClassMate2 get a message:" + msg);
	}
}
