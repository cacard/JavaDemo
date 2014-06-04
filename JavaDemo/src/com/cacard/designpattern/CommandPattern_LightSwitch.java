/**
 * TODO 命令模式，以灯泡/开关为例
 * 
 */

package com.cacard.designpattern;

public class CommandPattern_LightSwitch {

}

/** 灯泡有亮、灭状态 */
class Light{
	private boolean isLighting=false;
	
	public void lightOn(){
		this.isLighting=true;
	}
	
	public void lightOff(){
		this.isLighting=false;
	}
}