/** 
 *
 * @author Gary Lu
 *
 * A high stack of books is not stable, so it need to be split into some small stacks.
 * 
 * Input (console): 
 *   original_stack_height - how many books in the original stack;
 *   max_hight - the maximum capacity of each stack;
 *   partition - how many stacks should an original stack be spilt into;
 *   
 * Expected output (console):
 *   How many small stacks are there in the end.
 *
 * Example:
 *   input: 13, 3, 2 (13 -> 7 + 6 -> 4 + 3 + 3 + 3 -> 2 + 2 + 3 + 3 + 3)
 *   output: 5
 *   
 *   input: 3, 2, 5 (3 -> 1 + 1 + 1)
 *   output: 3
 * 
 * Vendasta pre-screening
 */

import java.io.*;

public class BookStack {

	public static void main(String[] args) {

		int original_stack_height;
		int max_height;
		int partition;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String[] input = br.readLine().split("\\s");
			if (input.length<3) {System.out.println("Invalid input - Insufficient parameters"); return;}
			original_stack_height = Integer.parseInt(input[0]);
			max_height = Integer.parseInt(input[1]);
			partition = Integer.parseInt(input[2]);
		}
		catch(IOException e) {
			System.out.println(e);
			return;
		}
		catch(NumberFormatException e) {
			System.out.println(e);
			return;
		}
		
		System.out.println("input: " + original_stack_height+" "+max_height+" "+partition);
		if (original_stack_height < 0 || max_height <= 0 || partition <= 0) {
			System.out.print("Invalid input - invalid parameters");
			return;
			}
		if (partition == 1 && original_stack_height > max_height) {
			System.out.print("Invalid input - not possible!");
			return;
			}
		System.out.println("result: " + split(original_stack_height, max_height, partition));
	}

	public static int split(int oldStack, int max, int partition) {
		if(oldStack == 0) return 0;
		if(oldStack <= max) return 1;
		int newStack = 0;
		if(oldStack % partition == 0 ) {
			newStack = oldStack/partition;
			return (partition * split(newStack, max, partition));
		}
		else {
			newStack = oldStack/partition + 1;
			return ( oldStack/newStack * split(newStack, max, partition) + split(oldStack%newStack, max, partition));
		}
		
	}
	
}
