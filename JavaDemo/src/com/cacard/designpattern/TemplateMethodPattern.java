/**
 * 模板方法模式
 * 父类规定了一个操作（骨架方法）需要一系列步骤来实现一个算法（或者一个任务），子类只需实现这些步骤对应的方法，最后调用父类的骨架方法完成该算法。
 */

package com.cacard.designpattern;

public class TemplateMethodPattern {

	public static void main(String[] args){
		Dialog d = new Dialog();
		d.draw();
	}
	
}


class View{
	
	protected void beforeDraw(){}
	protected void drawing(){}
	protected void afterDraw(){}
	
	/* templete method ： 用来做算法的骨架 */
	public void draw(){
		// 步骤1
		this.beforeDraw();
		// 步骤2
		this.drawing();
		// 步骤3
		this.afterDraw();
	}
}

class Dialog extends View{
	protected void beforeDraw(){
		System.out.println("beforeDraw");
	}
	protected void drawing(){
		System.out.println("drawing");
	}
	protected void afterDraw(){
		System.out.println("afterDraw");
	}
}