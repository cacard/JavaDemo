/**
 * 策略模式
 * <p>即对算法具体实现的依赖注入。<p>
 */

package com.cacard.designpattern;

public class StrategyPattern {

	public static void main(String[] args){
		Strategy sSub= new StrategySub();
		Client c = new Client(sSub);
		c.execute(1, 2);
	}
	
}

/* 算法接口 */
interface Strategy{
	int execute(int a,int b);
}

/* 算法1 */
class StrategyAdd implements Strategy{
	@Override
	public int execute(int a, int b) {
		return a+b;
	}
}

/* 算法2 */
class StrategySub implements Strategy{
	@Override
	public int execute(int a, int b) {
		return a-b;
	}
}

/* 算法使用者 */
class Client{
	private Strategy strategy;
	public Client(Strategy s){ /* 构造函数依赖注入*/
		this.strategy=s;
	}
	
	public int execute(int a,int b){
		return this.strategy.execute(a, b);
	}
}