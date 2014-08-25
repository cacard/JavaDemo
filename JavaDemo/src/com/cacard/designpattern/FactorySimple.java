/**
 * �򵥹���
 * 
 * 		������23��ģʽ֮һ
 * 		��������ݴ��ݽ����Ĳ���������һ������ĳ����������������Ĺ�ͬ�ӿڡ�
 */

package com.cacard.designpattern;

public class FactorySimple {

	public static void main(String[] args) {
		// in Client
		IProduct p = ProductFactory.createProduct("B");
	}

}

// ����ӿ�
interface IProduct {

}

// ����1
class ProductA implements IProduct {

}

// ����2
class ProductB implements IProduct {

}

// ������
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