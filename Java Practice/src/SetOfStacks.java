/**
 * 
 * @author Gary Lu
 * 
 *<Cracking the Coding Interview> 4th edition
 * 
 * Chapter 3
 * 3.3
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple. 
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. 
 * Implement a data structure SetOfStacks that mimics this. 
 * SetOfStacks should be composed of several stacks, and should create a new stack once the previous one exceeds capacity. 
 * SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack 
 * (that is, pop() should return the same values as it would if there were just a single stack).
 * 
 * Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
 * 
 */
//import java.io.*;
import java.util.*;

public class SetOfStacks {

	public static void main(String[] args) {
		SetOfStacks set = new SetOfStacks(3);
		for(int i=0; i<10; i++) {
			set.push(i);
			System.out.println("Push: "+i+"\ton Stack "+set.getNumOfStacks()+"\t Stack height "+set.getLastStackHeight());
		}
		
		for(int i=0; i<10; i++) {
			int num = set.getNumOfStacks();
			int last = set.getLastStackHeight();
			System.out.println("Pop: "+set.pop()+"\ton Stack "+num+"\t Stack height "+last);
		}

	}

	private ArrayList<Stack<Integer>> stacks;
	private int MaxHeight;
	private int LastStackHeight;
	private int NumOfStacks;
	
	SetOfStacks(int MaxHeight){
		if(MaxHeight <= 0) {System.out.println("Wrong Input!"); this.MaxHeight = 0; return;}
		this.MaxHeight = MaxHeight;
		this.stacks = new ArrayList<Stack<Integer>>();
	}
	
	Integer pop() {
		if(NumOfStacks == 0) {
			System.out.println("Stack is Empty!");
			return Integer.valueOf(-9999);
		}
		Integer result = stacks.get(NumOfStacks-1).pop();
		--LastStackHeight;
		if(LastStackHeight==0) {
			stacks.remove(NumOfStacks-1);
			--NumOfStacks;
			LastStackHeight = NumOfStacks==0?0:MaxHeight;
		}
		return result;
	}
	
	Integer push(Integer n) {
		if(stacks.isEmpty() || LastStackHeight == MaxHeight) {
			stacks.add(new Stack<Integer>());
			++NumOfStacks;
			LastStackHeight = 1;
			stacks.get(NumOfStacks-1).push(n);
			return n;
		}
		else {
			stacks.get(NumOfStacks-1).push(n);
			++LastStackHeight;
			return n;
		}
	}
	
	int getNumOfStacks() {return NumOfStacks;}
	int getLastStackHeight() {return LastStackHeight;}
}
