/**
 * TODO ����ģʽ���Ե���/����Ϊ��
 * 
 */

package com.cacard.designpattern;

public class CommandPattern_LightSwitch {

}

/** ������������״̬ */
class Light{
	private boolean isLighting=false;
	
	public void lightOn(){
		this.isLighting=true;
	}
	
	public void lightOff(){
		this.isLighting=false;
	}
}