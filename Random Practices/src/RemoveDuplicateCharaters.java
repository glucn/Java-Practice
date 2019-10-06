/**
 * 
 * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 1
 * 1.3
 * Design an algorithm and write code to remove the duplicate characters in a string 
 * without using any additional buffer. 
 * NOTE: One or two additional variables are fine. An extra copy of the array is not.
 *
 *
 */
import java.io.*;

public class RemoveDuplicateCharaters {

	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			String output = RemoveIt(input);
			System.out.println(output);
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}	
	}

	/* complexity O(N^3), not good
	public static String RemoveIt(String input) {
		if(input == null || input.equals("")) return input;
		char[] inputChar = input.toCharArray();
		int length = inputChar.length;
		for (int i = 0; i<length-1; i++) {
			for(int j = i+1; j< length; j++) {
				if(inputChar[i]==inputChar[j]) {
					for(int k=j; k< length-1; k++) inputChar[k] = inputChar[k+1];
					inputChar[length-1] = '*';
					length--;
					j--;
				}
			}
		}
		return new String(inputChar, 0, length);
	}
	*/
	
	// complexity O(N^2)
	// introduced in the book
	public static String RemoveIt(String input) {
		if(input == null || input.equals("")) return input;
		char[] inputChar = input.toCharArray();
		int length = inputChar.length;
		if (length < 2) return input;
		
		int tail = 1;
		for(int i=1; i<length; i++) {
			int j;
			for(j=0; j<tail; j++) {
				if (inputChar[i]==inputChar[j]) break;
			}
			if(j==tail) {
				inputChar[tail]=inputChar[i];
				tail++;
			}
		}
		return new String(inputChar, 0, tail);
	}
}
