import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestNode {
	
	Node one;
	Node two;
	Node three;
	Node four;
	
	//creating a test matrix of:
	// [(10), (20)]
	// [(30), (40)]

	@Before
	public void setUp() throws Exception {
		this.one = new Node(10);
		this.two = new Node(20);
		this.three = new Node(30);
		this.four = new Node(40);
		one.setrow(0);
		one.setcolumn(0);
		one.setdown(three);
		one.setright(two);
		two.setrow(0);
		two.setcolumn(1);
		two.setdown(four);
		two.setright(one);
		three.setrow(1);
		three.setcolumn(0);
		three.setdown(one);
		three.setright(four);
		four.setrow(1);
		four.setcolumn(1);
		four.setdown(two);
		four.setright(three);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testdown() {
		assertTrue(one.getdown()==three);
		assertTrue(two.getdown()==four);
		assertTrue(three.getdown()==one);
		assertTrue(four.getdown()==two);
	}
	
	@Test
	public void testvalue(){
		assertTrue(one.getvalue()==10);
		assertTrue(two.getvalue()==20);
		assertTrue(three.getvalue()==30);
		assertTrue(four.getvalue()==40);
	}

}
