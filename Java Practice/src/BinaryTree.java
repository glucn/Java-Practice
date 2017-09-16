/**
 * 
 * @author Gary Lu
 * 
 * <Cracking the Coding Interview> 4th edition
 * 
 * Chapter 4
 * 
 * Binary tree algorithm practice
 *
 */
//import java.util.*;

public class BinaryTree {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		
		for(int i = 0; i<5; i++) {
			bt.InsertNode((int)(Math.random()*10));
		}
		
		System.out.println("PreOrder:");
		bt.PreOrderTraversal();
		System.out.println("\nInOrder:");
		bt.InOrderTraversal();
		System.out.println("\nPostOrder");
		bt.PostOrderTraversal();
		System.out.println();
		
		if(bt.isBalanced()) System.out.println("Balanced Tree");
		else System.out.println("Not a Balanced Tree");
		
		System.out.println(bt.root.NextNodeInOrder().value);
	}

	Node root;
	//int depth;
	
	BinaryTree(){
		this.root = null;
		//depth = 0;
	}
	
	boolean isBalanced() {
		if(root.MaxDepth() <= root.MinDepth() + 1) return true;
		else return false;
	}
	
	void PreOrderTraversal(Node n) {
		if(n == null) return;
		System.out.print(n.value+" ");
		PreOrderTraversal(n.left);
		PreOrderTraversal(n.right);
	}
	
	void InOrderTraversal(Node n) {
		if(n == null) return;
		InOrderTraversal(n.left);
		System.out.print(n.value+" ");
		InOrderTraversal(n.right);
	}
	
	void PostOrderTraversal(Node n) {
		if(n == null) return;
		PostOrderTraversal(n.left);
		PostOrderTraversal(n.right);
		System.out.print(n.value+" ");	
	}
	
	void PreOrderTraversal() {PreOrderTraversal(this.root);}
	void InOrderTraversal() {InOrderTraversal(this.root);}
	void PostOrderTraversal() {PostOrderTraversal(this.root);}
	
	void InsertNode(int n) {
		if (root==null) root = new Node(n);
		else root.InsertNode(n);
	}
}

class Node{
	int value;
	Node parent;
	Node left;
	Node right;
	
	Node(int value){
		this.value = value;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	Node(int value, Node parent){
		this.value = value;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}
	
	Node(int value, Node parent, Node left, Node right){
		this.value = value;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	
	// find Max of depth under this node
	int MaxDepth() {
		int LeftMaxDepth, RightMaxDepth;
		if(left==null) LeftMaxDepth = 0;
		else LeftMaxDepth = left.MaxDepth();
		if(right==null) RightMaxDepth = 0;
		else RightMaxDepth = right.MaxDepth();
		if(LeftMaxDepth >= RightMaxDepth) return(LeftMaxDepth+1);
		else return(RightMaxDepth+1);
	}
	
	// find Min of depth under this node
	int MinDepth() {
		int LeftMinDepth, RightMinDepth;
		if(left==null) LeftMinDepth = 0;
		else LeftMinDepth = left.MinDepth();
		if(right==null) RightMinDepth = 0;
		else RightMinDepth = right.MinDepth();
		if(LeftMinDepth <= RightMinDepth) return(LeftMinDepth+1);
		else return(RightMinDepth+1);
	}
	
	void InsertNode(int n) {
		if (n<= this.value) {
			if(this.left == null) {this.left=new Node(n, this); System.out.println("Add "+n+" as the left leaf of "+this.value);}
			else this.left.InsertNode(n);
		}
		else {
			if(this.right == null) {this.right=new Node(n, this); System.out.println("Add "+n+" as the right leaf of "+this.value);}
			else this.right.InsertNode(n);
		}
	}
	
	Node NextNodeInOrder() {
		if(this.parent == null || this.right != null) {
			// bug here!! if this.right is null, NullPointerException
			return(this.right.LeftMostNode());
		}
		else {
			Node parentNode = this.parent;
			if (parentNode.left == this) return parentNode;
			else return(parentNode.NextNodeInOrder());
		}
		
	}
	
	Node LeftMostNode() {
		if(this == null ) return null;
		if(this.left == null) return this;
		else {
			Node index = this.left;
			while(index.left != null) {
				index = index.left;
			}
			return index;
		}
	}
}
