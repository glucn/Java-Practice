/**
 * 
 * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 1
 * 1.1
 * Implement an algorithm to determine if a string has all unique characters
 * What if you can not use additional data structures?
 *
 */
import java.io.*;

public class UniqueCharacters {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			char[] inputChar = br.readLine().toCharArray();
			if (inputChar.length == 0) {System.out.println("Please input a string."); return;}
			int[] count = new int[256]; //need to be extended if input string include any non-ASCII characters
			boolean flag=false;
			for(char c: inputChar) {
				count[c]++;
				//if(count[c] >1 ) System.out.println("Find a repetitive character: "+c);
				if (!flag && count[c] > 1) flag = true;
				}
			if(flag) {
				System.out.println("Repetitive characters:");
				for(int i=0; i<256; i++) if (count[i]>1) System.out.println((char)i+"\t"+ count[i]);
			}
			else {
				System.out.println("All characters are unique.");
			}
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}
	}

}
