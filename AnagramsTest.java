package hw6;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void test() {
		Anagrams a = new Anagrams();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals((long)maxEntries.get(0).getKey(), 236204078);
		assertEquals(maxEntries.get(0).getValue().size(), 15);
		
		assertEquals(a.myhashcode("ponies"), 1815672463);
	}

}
