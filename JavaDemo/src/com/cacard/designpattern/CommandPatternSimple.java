/**
 * ����ģʽ
 * 
 * ����˵�����ǰ�ĳЩ���������ó���������ɸ������ͨ��ֻ��һ��Execute()������Ϊִ�У���
 * ������������ͬ����Ϳ���ִ�в�ͬ��������ɵļ��ϡ�
 * 
 * ����û������ģʽ����Ҫ��һ�������д��һ��ί�м����У���Javaû��ί�С�
 */


package com.cacard.designpattern;

import java.util.ArrayList;
import java.util.List;

public class CommandPatternSimple {

	public static void main(String[] args){
		Context1 ctx = new Context1();
		executeCommandSequence1(ctx);
	}
	
	/* ִ��һ������ */
	private static void executeCommandSequence1(Context1 ctx)
	{
		List<ContextCommandBase> commands = new ArrayList<>();
		commands.add(new Command1(ctx));
		commands.add(new Command1(ctx));
		commands.add(new Command1(ctx));
		
		for(ContextCommandBase cmd : commands){
			cmd.execute();
		}
		
	}
	
}


class ContextCommandBase
{
	protected Context1 ctx;
	
	public ContextCommandBase(Context1 ctx){
		this.ctx=ctx;
	}
	
	public void execute(){
		
	}
}

class Command1 extends ContextCommandBase
{
	public Command1(Context1 ctx){
		super(ctx);
	}
	
	@Override
	public void execute() {
		this.ctx.command1();
	}
}
// ... ��������

class Context1
{
	// ��Ӧһ���������ط����Լ����������ʵ���Լ��ŵ�Command��
	public void command1(){
		System.out.println("->command1");
	}
}