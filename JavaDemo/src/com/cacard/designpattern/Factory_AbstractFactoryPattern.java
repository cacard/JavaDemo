package com.cacard.designpattern;

/**
 * Abstract Factory Pattern
 * 
 * Provide an interface for creating families of related or dependent objects
 * without specifying their concrete classes
 * 
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
public class Factory_AbstractFactoryPattern {

	public static void main(String[] args) {
		CarFactory factory = new BenzCarFactory();
	}

}