/**
 * 命令模式
 * 
 * 简单来说，就是把某些方法单独拿出来，抽象成各个命令（通常只有一个Execute()函数作为执行）。
 * 这样，其它不同的类就可以执行不同的命令组成的集合。
 * 
 * 假若没有命令模式，需要把一组命令集合写在一组委托集合中，但Java没有委托。
 */


package com.cacard.designpattern;

import java.util.ArrayList;
import java.util.List;

public class CommandPatternSimple {

	public static void main(String[] args){
		Context1 ctx = new Context1();
		executeCommandSequence1(ctx);
	}
	
	/* 执行一组命令 */
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
// ... 其它命令

class Context1
{
	// 对应一个命令，这个地方可以继续解耦，把其实现自己放到Command中
	public void command1(){
		System.out.println("->command1");
	}
}