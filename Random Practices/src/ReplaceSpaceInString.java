/**
 * 
 * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 1
 * 1.5
 * Write a method to replace all spaces in a string with ‘%20’.
 *
 */
import java.io.*;

public class ReplaceSpaceInString {

	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			System.out.println(ReplaceSpace(input));
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}
	}

	public static String ReplaceSpace(String input) {
		if(input==null | input.equals("")) return input;
		char[] inputArray = input.toCharArray();
		char[] outputArray = new char[inputArray.length*3]; 
		// This is to prepare for the worst case - all characters are space.
		// Another option is to go through the String first to find out the number of spaces,
		// and allocate space for the outputArray accordingly.
		// Trade-off : space vs time
		int j = 0;
		
		for(int i = 0; i<inputArray.length; i++) {
			if(inputArray[i] != ' ') {
				outputArray[j] = inputArray[i];
				j++;
			}
			else {
				outputArray[j]='%';
				outputArray[j+1]='2';
				outputArray[j+2]='0';
				j=j+3;
			}
		}
		return String.valueOf(outputArray, 0, j);
	}
}
