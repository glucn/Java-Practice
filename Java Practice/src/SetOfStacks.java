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
			System.out.println("Push: "+i+"\ton Stack "+(set.getNumOfStacks()-1)+"\t Stack height "+set.getLastStackHeight());
		}
		
		/*
		for(int i=0; i<11; i++) {
			int num = set.getNumOfStacks();
			int last = set.getLastStackHeight();
			System.out.println("Pop: "+set.pop()+"\ton Stack "+num+"\t Stack height "+last);
		}
		*/
		
		for(int i=0; i<11; i++) {
			System.out.println("Pop: "+set.popAt(1)+"\ton Stack "+ 1);
		}

	}

	private ArrayList<Stack<Integer>> stacks;
	private int MaxHeight;
	
	SetOfStacks(int MaxHeight){
		if(MaxHeight <= 0) {System.out.println("Wrong Input!"); this.MaxHeight = 0; return;}
		this.MaxHeight = MaxHeight;
		this.stacks = new ArrayList<Stack<Integer>>();
	}
	
	Stack<Integer> getLastStack(){
		if(stacks.size()==0) return null;
		else return stacks.get(stacks.size()-1);
	}
	
	Integer pop() {
		if(stacks.size() == 0) {
			System.out.println("Stack is Empty!");
			return Integer.valueOf(-999999);
		}
		Stack<Integer> last = this.getLastStack();
		Integer result = last.pop();
		if(last.size()==0) {
			stacks.remove(stacks.size()-1);
		}
		return result;
	}
	
	Integer push(Integer n) {
		Stack<Integer> last = this.getLastStack();
		if(last==null || last.size() == this.MaxHeight) {
			Stack<Integer> newStack = new Stack<Integer>();
			newStack.push(n);
			stacks.add(newStack);
			return n;
		}
		else {
			last.push(n);
			return n;
		}
	}
	
	Integer popAt(int index) {
		//Stack<Integer> last = this.getLastStack();
		if(index >= stacks.size()) {
			System.out.println("There is no such stack!");
			return Integer.valueOf(-999999);
		}
		else if(stacks.size() == 0) {
			System.out.println("Stack is Empty!");
			return Integer.valueOf(-999999);
		} 
		else {
			Stack<Integer> targetStack = stacks.get(index);
			Integer number = targetStack.pop();
			if(targetStack.isEmpty()) {
				stacks.remove(index);
			}
			return number;
			// Problem is: not all the stacks are full after popAt() 
			// There is a tradeoff to roll the bottom of next stack onto the top of this stack.
		}
	}
	
	
	int getNumOfStacks() {return stacks.size();}
	int getLastStackHeight() {
		Stack<Integer> last = this.getLastStack();
		if(last==null) return 0;
		else return(last.size());
	}
}
