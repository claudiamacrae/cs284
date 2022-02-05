package hw5;
import static org.junit.Assert.*;

import org.junit.Test;



public class TreapTest {
	
	@Test
	public void test() {
		Treap<Integer> testTree =new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		
		assertTrue(testTree.find(6));
		assertTrue(testTree.delete(6));
		assertFalse(testTree.find(6));
		assertTrue(testTree.find(7));
		assertFalse(testTree.add(3));
	}

}
