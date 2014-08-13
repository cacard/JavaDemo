/**
 * Builder模式
 * 使用一个类来构造另外一个类
 * 常见的有StringBuilder/Notification.Builder
 */

package com.cacard.designpattern;

public class BuilderPattern {

	public static void main(String[] args) {
		CarBuilder builder = new CarBuilder();
		builder.setName("das auto").setWeight(2000);
		Car c = builder.build();
	}

}

/** 被构造的类 */
class Car {
	private String name;
	private int weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

/** 构造Car的专用类 */
class CarBuilder {
	private Car car;

	public CarBuilder() {
		this.car = new Car();
	}

	public CarBuilder setName(String name) {
		this.car.setName(name);
		return this;
	}

	public CarBuilder setWeight(int w) {
		this.car.setWeight(w);
		return this;
	}

	public Car build() {
		return car;
	}
}