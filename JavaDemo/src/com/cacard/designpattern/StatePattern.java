/**
 * ״̬ģʽ
 * 
 * ��ͬ��״̬������ͬ�Ķ���������Ҫ�Ѹ���״̬���������г���ͨ������£���ͨ��ö������ʾ״̬������������ִ�����if/else����������չ��
 * ״̬ģʽ������ʵ��״̬�Զ��л���
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
		ctx.state=new StateB();//״̬ת��
	}
}

class StateB implements StateBase
{
	int count=0;
	public void Handle(Context ctx,String str)
	{
		System.out.println("@StateB->"+str);
		
		// ��StateB���������κ�ת��StateA
		if(count>=1){
			ctx.state=new StateA();
		}
		
		count++;
	}
}

/* ����״̬���� */
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