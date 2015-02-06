package com.cacard.jvm;

public class Javap_MethodCount {

}

class Javap_Base {
	protected void MyMethod(){
		
	}
}

class Javap_Sub extends Javap_Base {
	public Javap_Sub () {
		MyMethod();
	}
}
