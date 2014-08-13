/**
 * Builderģʽ
 * ʹ��һ��������������һ����
 * ��������StringBuilder/Notification.Builder
 */

package com.cacard.designpattern;

public class BuilderPattern {

	public static void main(String[] args) {
		CarBuilder builder = new CarBuilder();
		builder.setName("das auto").setWeight(2000);
		Car c = builder.build();
	}

}

/** ��������� */
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

/** ����Car��ר���� */
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