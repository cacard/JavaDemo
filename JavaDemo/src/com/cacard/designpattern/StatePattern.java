/**
 * 状态模式
 * 
 * 不同的状态触发不同的动作，就需要把各个状态拉出来进行抽象。通常情况下，会通过枚举来表示状态，但这样会出现大量的if/else，且难以扩展。
 * 状态模式还可以实现状态自动切换。
 * 
 * @see http://en.wikipedia.org/wiki/State_pattern
 */

package com.cacard.designpattern;

public class StatePattern {

	public static void main(String[] args){
		Context ctx = new Context();
		ctx.writeName("good1");
		ctx.writeName("good2");
		ctx.writeName("good3");
		ctx.writeName("good4");
		ctx.writeName("good5");
		ctx.writeName("good6");
	}
	
}

interface StateBase
{
	void Handle(Context ctx,String name);
}

class StateA implements StateBase
{
	public void Handle(Context ctx,String str)
	{
		System.out.println("@StateA->"+str);
		ctx.state=new StateB();//状态转换
	}
}

class StateB implements StateBase
{
	int count=0;
	public void Handle(Context ctx,String str)
	{
		System.out.println("@StateB->"+str);
		
		// 当StateB被调用两次后，转向StateA
		if(count>=1){
			ctx.state=new StateA();
		}
		
		count++;
	}
}

/* 包含状态的类 */
class Context
{
	public StateBase state;
	
	public Context(){
		this.state=new StateA();
	}
	
	public void writeName(String str){
		state.Handle(this, str);
	}
}