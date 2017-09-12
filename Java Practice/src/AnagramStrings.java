/**
 * 
 * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 1
 * 1.4
 * Write a method to decide if two strings are anagrams or not.
 *
 */
import java.io.*;
import java.util.*;

public class AnagramStrings {

	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str_a = br.readLine();
			String str_b = br.readLine();
			System.out.println(isAnagram(str_a, str_b));
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}
	}

	// practice with HashMap
	// could be implemented with an int[256] array
	public static boolean isAnagram(String a, String b) {
		if(a.length() != b.length()) return false;
		HashMap<Character, Integer> count = new HashMap<Character, Integer>();
		
		for(char c: a.toCharArray()) {
			Integer n = count.getOrDefault(Character.valueOf(c), Integer.valueOf(0));
			count.put(Character.valueOf(c), n+1);
		}
		for(char c: b.toCharArray()) {
			Integer n = count.getOrDefault(Character.valueOf(c), Integer.valueOf(0));
			count.put(Character.valueOf(c), n-1);
		}
		for(Character c : count.keySet()) {
			if(count.get(c) != 0) return false;
		}
		return true;
	}
}
