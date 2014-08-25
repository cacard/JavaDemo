/**
 * 工厂方法
 * 
 * 		一族类的每个类均有自己的工厂类。<br/>
 * 		Client只依赖于接口（族类接口和工厂接口）
 */

package com.cacard.designpattern;

public class FactoryMethod {

	public static void main(String[] args) {
		IFactory factory = new ProductBFactory();
		IProduct b = factory.createProduct();
	}

}

/** 工厂类通用接口 */
interface IFactory {
	IProduct createProduct();
}

/** ProductA的工厂类 */
class ProductAFactory implements IFactory {
	public IProduct createProduct() {
		return new ProductA();
	}
}

/** ProductB的工厂类 */
class ProductBFactory implements IFactory {
	public IProduct createProduct() {
		return new ProductA();
	}
}
