/**
 * 
 * @author Gary Lu
 * 
 * Input (console): 
 *   The first line should be a integer number N, the following lines should be strings.
 *
 * Expected Output (console):
 *   Reverse the sequence of characters in every words of N lines.
 * 
 * Example:
 * input
 * 2
 * abc de fg
 * hijk lmn
 * 
 * output
 * cba ed gf
 * kjih nml
 * 
 * 
 * Vendasta pre-screening
 */

import java.io.*;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class ReverseString {

	public static void main(String[] args) {
		int n = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			n = Integer.parseInt(br.readLine());
			if (n<=0) {System.out.println("Invalid input!"); return;}
			String[] line = new String[n];
			for(int i=0; i<n ; i++) {
				line[i] = new String(br.readLine());
			}
			for(int i=0; i<n; i++) {
				System.out.println(reverseWords(line[i]));
			}
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}
		catch(NumberFormatException e) {
			System.out.println(e);
			return;
		}
		catch(NullPointerException e) {
			System.out.println(e);
			return;
		}
	}
	
	public static String reverseWords(String line) {
		if (line == null || line.equals("")) return line;
		int i = 0 , j = 0;
		char[] input = line.toCharArray();
		char[] result = new char[input.length];
		for (i=0; i<line.length(); ++i) {
			if (isDelimiter(input[i]) || i == line.length()-1) {
				for (int k=0; k<i-j; k++) {
					result[j+k] = input [i-k-1];
				}
				result[i]=input[i];
				j=i+1;
			}			
		}
		return new String(result);
	}
	
	public static boolean isDelimiter(char c) {
		return(c==' ' || c==',' || c=='.' || c==';' || c== '?');
	}
}
