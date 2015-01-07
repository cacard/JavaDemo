package com.cacard.designpattern;

/**
 * Factory Method
 * 
 * Definition: Define an interface for creating an object, but let subclasses
 * decide which class to instantiate. Factory Method lets a class defer
 * instantiation to subclasses.
 * 
 * �е�ģ�巽����ζ����ģ�巽ʽ�Ĳ��ص��ǹ����㷨�Ǽܣ��������������ص������ഴ������Ķ���
 * 
 * @author cunqingli
 * 
 */

interface IWheel {
}

interface IEngine {
}

interface IWindow {
}

class CarFactory {
	IWheel wheel = null;
	IEngine engine = null;
	IWindow window = null;

	public CarFactory() {
		wheel = createWheel();
		engine = createEngine();
		window = createWindow();
	}

	protected IWheel createWheel() {
		return null;
	}

	protected IEngine createEngine() {
		return null;
	}

	protected IWindow createWindow() {
		return null;
	}
}

// -----------------------------------
// Benz Relate

class BenzWheel implements IWheel {
}

class BenzEngine implements IEngine {
}

class BenzWindow implements IWindow {
}

class BenzCarFactory extends CarFactory {
	protected IWheel createWheel() {
		return new BenzWheel();
	}

	protected IEngine createEngine() {
		return new BenzEngine();
	}

	protected IWindow createWindow() {
		return new BenzWindow();
	}
}

// ----------------------------------
// test
public class FactoryMethod3 {

	public static void main(String[] args) {
		CarFactory factory = new BenzCarFactory();
	}

}