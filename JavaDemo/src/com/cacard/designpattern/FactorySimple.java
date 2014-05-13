/**
 * 简单工厂
 * <p>并不是23种模式之一</p>
 * #1 一族类的共同接口
 * #2 工厂类
 * #3 Client
 */

package com.cacard.designpattern;

public class FactorySimple {

	public static void main(String[] args){
		// in Client
		IProduct p = ProductFactory.createProduct("B");
	}
	
}

interface IProduct{
	
}

class ProductA implements IProduct{
	
}

class ProductB implements IProduct{
	
}

/** 工厂类 */
class ProductFactory{
	public static IProduct createProduct(String productType){
		if(productType.equals("A")){
			return new ProductA();
		}
		if(productType.equals("B")){
			return new ProductB();
		}
		return null;
	}
}