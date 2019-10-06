/**
 *  * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 3
 * 3.2
 * How would you design a stack which, in addition to push and pop, 
 * also has a function min which returns the minimum element? 
 * Push, pop and min should all operate in O(1) time
 * 
 */
import java.util.*;
import java.io.*;

// This is implemented with storing minimum value in each node.
// Another approach is to build another stack (call it MinStack), which is only for the minimum values.
// When push into main stack, check if the value <= the top on MinStack: yes, push it into MinStack.
// When pop from main stack, check if the value = the top on MinStack: yes, pop it out from MinStack.
// That approach might be more space efficient.
public class StackWithMin extends Stack<NodeWithMin> {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			StackWithMin stack = new StackWithMin();
			while(true) {
				input = br.readLine();
				if(input.equals("")) break;
				stack.push(Integer.parseInt(input));
				System.out.println("Stack Top: "+ stack.peek().getValue()+"\tStack Min: "+stack.peek().getMin());
			}
			
			while(stack.top!=null) {
				NodeWithMin node = stack.pop();
				System.out.println("Pop: "+ node.getValue() + "\tStack Min: "+node.getMin());
			}
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}

	}
	
	NodeWithMin top;
	
	// @override
	public NodeWithMin push(int n) {
		NodeWithMin node = new NodeWithMin(n);
		if (this.top==null) {
			node.setMin(n);
			this.top=node;
			return this.top;
		}
		if (n<this.top.getMin()) {
			node.setMin(n);
			node.setNext(this.top);
			this.top = node;
		}
		else {
			node.setMin(this.top.getMin());
			node.setNext(this.top);
			this.top = node;
		}
		return this.top;
	}
	
	// @override
	public NodeWithMin pop() {
		if (this.top == null) {System.out.println("The stack is empty!");}
		NodeWithMin node = this.top;
		this.top = this.top.getNext();
		return node;
	}
	
	// @override
	public NodeWithMin peek() {
		return this.top;
	}
	
	public int min() {
		return this.top.getMin();
	}
	
}

class NodeWithMin {
	private int value;
	private int min;
	private NodeWithMin next;
	
	NodeWithMin(){
		value = 0;
		min = 0;
	}
	
	NodeWithMin(int value){
		this.value = value;
	}
	
	NodeWithMin(int value, int min, NodeWithMin next){
		this.value = value;
		this.min = min;
		this.next = next;
	}
	
	int getValue() {
		return this.value;
	}
	
	int getMin() {
		return this.min;
	}
	
	NodeWithMin getNext() {
		return this.next;
	}
	
	void setValue(int value) {
		this.value = value;
	}
	
	void setMin(int min) {
		this.min = min;
	}
	
	void setNext(NodeWithMin next) {
		this.next = next;
	}
}
