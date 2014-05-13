/**
 * ģ�巽��ģʽ
 * ����涨��һ���������Ǽܷ�������Ҫһϵ�в�����ʵ��һ���㷨������һ�����񣩣�����ֻ��ʵ����Щ�����Ӧ�ķ����������ø���ĹǼܷ�����ɸ��㷨��
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
	
	/* templete method �� �������㷨�ĹǼ� */
	public void draw(){
		// ����1
		this.beforeDraw();
		// ����2
		this.drawing();
		// ����3
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