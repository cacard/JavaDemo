/**
 * Mediator Pattern Simple
 * Client不与一族对象进行交互，而是与“中介”交互
 * 本例类似于Log到不同的Log实现一样
 * 
 */

package com.cacard.designpattern;

// Client 与 Mediator交互
public class MediatorPatternSimple {

	public static void main(String[] args){

		Mediator m = new Mediator(); // Mediator与 ClassMate交互
		m.sayToSomeClassMate("hello");
		
	}
	
}

/** 中介者 */
class Mediator{
	
	private IClassMate mate;
	
	public Mediator(){
		this.mate=new ClassMate2();
	}
	
	public void sayToSomeClassMate(String s){
		mate.say(s);
	}
}

/** 族类接口 */
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