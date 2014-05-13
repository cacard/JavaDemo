/**
 * WebService Demo
 */

package com.cacard.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class Hello {
	
	public Hello(){}
	
	public String sayHello(@WebParam(name="msg") String msg)
	{
		return "Hello,"+msg;
	}

}
