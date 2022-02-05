package hw3;

import static org.junit.Assert.*;
import org.junit.*;

public class IDLListTest {

	@Test
	public void test1() {
		IDLList<Integer> l = new IDLList<Integer>();
		l.add(3);
		l.add(0, 2);
		l.add(1, 9);
		l.append(18);
		assertEquals("[2, 9, 3, 18]", l.toString());
		assertEquals(4, l.size());
		assertEquals(2, (int)l.getHead());
		assertEquals(18, (int)l.getTail());
		assertEquals(9, (int)l.get(1));
		assertEquals(18, (int)l.removeLast());
		assertFalse(l.remove(42));
	}

	public void test2() {
		IDLList<Integer> l = new IDLList<Integer>();
		assertEquals(null, l.getHead());
		assertEquals(null, l.getTail());
		l.append(15);
		assertEquals(15, (int)l.getHead());
		int removed = l.removeAt(0);
		assertEquals(15, removed);
		try {
			l.remove();
			assert(false);
		}catch(IllegalStateException e) {
			assert(true);
		}
	}
}
