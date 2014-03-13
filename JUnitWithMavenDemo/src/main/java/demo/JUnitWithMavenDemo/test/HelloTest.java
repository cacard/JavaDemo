package demo.JUnitWithMavenDemo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import demo.JUnitWithMavenDemo.*;

public class HelloTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSayHello() {
		String result = new Hello().sayHello();
		assertEquals(result,"hello");
	}

	@Test
	public void testAdd() {
		int result = new Hello().add(1, 2);
		assertEquals(result,3);
	}

}
