/**
 * ��������
 * 
 * 		һ�����ÿ��������Լ��Ĺ����ࡣ<br/>
 * 		Clientֻ�����ڽӿڣ�����ӿں͹����ӿڣ�
 */

package com.cacard.designpattern;

public class FactoryMethod {

	public static void main(String[] args) {
		IFactory factory = new ProductBFactory();
		IProduct b = factory.createProduct();
	}

}

/** ������ͨ�ýӿ� */
interface IFactory {
	IProduct createProduct();
}

/** ProductA�Ĺ����� */
class ProductAFactory implements IFactory {
	public IProduct createProduct() {
		return new ProductA();
	}
}

/** ProductB�Ĺ����� */
class ProductBFactory implements IFactory {
	public IProduct createProduct() {
		return new ProductA();
	}
}
