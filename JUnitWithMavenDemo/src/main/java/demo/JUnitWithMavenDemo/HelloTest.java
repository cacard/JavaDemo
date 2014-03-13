package demo.JUnitWithMavenDemo;

import junit.framework.TestCase;

public class HelloTest extends TestCase {
	public void testHello()
	{
		assertEquals(new Hello().sayHello(),"hello");
	}
}
