/**
 * 简单工厂
 * 
 * 		并不是23种模式之一
 * 		工厂类根据传递进来的参数来创建一族对象的某个对象，依赖于族类的共同接口。
 */

package com.cacard.designpattern;

public class FactorySimple {

	public static void main(String[] args) {
		// in Client
		IProduct p = ProductFactory.createProduct("B");
	}

}

// 族类接口
interface IProduct {

}

// 族类1
class ProductA implements IProduct {

}

// 族类2
class ProductB implements IProduct {

}

// 工厂类
class ProductFactory {
	public static IProduct createProduct(String productType) {
		if (productType.equals("A")) {
			return new ProductA();
		}
		if (productType.equals("B")) {
			return new ProductB();
		}
		return null;
	}
}