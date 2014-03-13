package demo.JUnitWithMavenDemo;

import static org.junit.Assert.*;
import org.junit.Test;

public class HelloTest4 {
	
	@Test
	public void testHello()
	{
		assertEquals(new Hello().sayHello(),"hello");
	}

}
