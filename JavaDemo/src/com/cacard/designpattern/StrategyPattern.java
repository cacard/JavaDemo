/**
 * ����ģʽ
 * <p>�����㷨����ʵ�ֵ�����ע�롣<p>
 */

package com.cacard.designpattern;

public class StrategyPattern {

	public static void main(String[] args){
		Strategy sSub= new StrategySub();
		Client c = new Client(sSub);
		c.execute(1, 2);
	}
	
}

/* �㷨�ӿ� */
interface Strategy{
	int execute(int a,int b);
}

/* �㷨1 */
class StrategyAdd implements Strategy{
	@Override
	public int execute(int a, int b) {
		return a+b;
	}
}

/* �㷨2 */
class StrategySub implements Strategy{
	@Override
	public int execute(int a, int b) {
		return a-b;
	}
}

/* �㷨ʹ���� */
class Client{
	private Strategy strategy;
	public Client(Strategy s){ /* ���캯������ע��*/
		this.strategy=s;
	}
	
	public int execute(int a,int b){
		return this.strategy.execute(a, b);
	}
}