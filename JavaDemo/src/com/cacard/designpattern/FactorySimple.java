/**
 * �򵥹���
 * <p>������23��ģʽ֮һ</p>
 * #1 һ����Ĺ�ͬ�ӿ�
 * #2 ������
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

/** ������ */
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