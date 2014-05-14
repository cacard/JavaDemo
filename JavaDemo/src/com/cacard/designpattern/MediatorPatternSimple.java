/**
 * Mediator Pattern Simple
 * Client����һ�������н����������롰�н顱����
 * ����������Log����ͬ��Logʵ��һ��
 * 
 */

package com.cacard.designpattern;

// Client �� Mediator����
public class MediatorPatternSimple {

	public static void main(String[] args){

		Mediator m = new Mediator(); // Mediator�� ClassMate����
		m.sayToSomeClassMate("hello");
		
	}
	
}

/** �н��� */
class Mediator{
	
	private IClassMate mate;
	
	public Mediator(){
		this.mate=new ClassMate2();
	}
	
	public void sayToSomeClassMate(String s){
		mate.say(s);
	}
}

/** ����ӿ� */
interface IClassMate{
	void say(String s);
}

class ClassMate1 implements IClassMate{
	@Override
	public void say(String s) {
		System.out.println("ClassMate1->"+s);
	}
}

class ClassMate2 implements IClassMate{
	@Override
	public void say(String s) {
		System.out.println("ClassMate2->"+s);
	}
}