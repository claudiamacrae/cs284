/**
 * @author Claudia MacRae
 * I pledge my honor I have abided by the Stevens Honor System.
 */
package hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	/**
	 * Builds letter table with letters from alphabet mapping to unique ordered primes
	 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		Character[] alpha = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for(int i = 0; i < 26; i++) {
			letterTable.put(alpha[i], primes[i]);
	    }
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/**
	 * Adds word to anagram table according to it's hash code, derived from the letter table
	 * @param s String to be added to anagramTable
	 */
	public void addWord(String s) {
		long hashCode = myhashcode(s);
		ArrayList<String> value;
		if(anagramTable.containsKey(hashCode)) {		//if hash is already in map
			value = anagramTable.get(hashCode);
		}else {											//else, make a new arrayList to add
			value = new ArrayList<String>();
		}
		value.add(s);
		anagramTable.put(hashCode, value);
	}
	
	/**
	 * Calculates hash code for string s according to letterTable hash values
	 * @param s String to convert to hash code
	 * @return Calculated hash code
	 */
	public long myhashcode(String s) {
		if(s == "") {
			throw new IllegalArgumentException("Empty String");
		}
		long result = 1;
	    for(int i = 0; i < s.length(); i++) {
	    	result = result*letterTable.get(s.charAt(i));
	    }
	    return result;

	}
	
	/**
	 * Adds words from file s.txt to anagramTable
	 * @param s Name of text file
	 * @throws IOException If Buffered reader fails
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * Parses anagramList for hashcode with the greatest number of correlating strings
	 * @return ArrayList of Entries in form <Hash code, ArrayList of Strings>
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    int maxCount = 0;
	    ArrayList<Map.Entry<Long, ArrayList<String>>> result= new ArrayList<Map.Entry<Long, ArrayList<String>>>();
	    for(Map.Entry<Long, ArrayList<String>> e: anagramTable.entrySet()) {
	    	if(e.getValue().size() > maxCount) {
	    		maxCount = e.getValue().size();
	    		result.clear();
	    		result.add(e);
	    	}
	    	else if(e.getValue().size() == maxCount) {
	    		result.add(e);
	    	}
	    }
	    return result;
	}
	
	/**
	 * Calculates longest list of anagrams in words_alpha.txt and prints it with the words' common hash code
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
