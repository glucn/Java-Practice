package UnionFind;

/**
 * 
 * @author Gary Lu
 *
 *  Successor with delete. Given a set of n integers S={0,1,...,n-1} and a sequence of requests of the following form:
 *  > Remove x from S
 *  > Find the successor of x: the smallest y in S such that y>=x.
 *  Design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 *  
 */
import edu.princeton.cs.algs4.QuickUnionUF;

public class SuccessorWithDelete {

	private QuickUnionUF unionFind;
	int size;
	
	SuccessorWithDelete(int n){
		size = n;
		unionFind = new QuickUnionUF(n+1);
		// add a virtual node at the last to handle the case of removing the last node
	}
	
	public void remove(int x) {
		if(isLast(x)) unionFind.union(x, size);
		// if x is the last node, union it with the virtual node
		else unionFind.union(x, successor(x));
		// else, union x with the successor of x
	}
	
	public int successor(int x) {
		if(isLast(x)) return(-1);
		else return unionFind.find(x+1);
	}
	
	private boolean isLast(int x) {
		if(x==size-1) return true;
		else if (unionFind.find(x+1) == size) return true;
		// if the root of x+1 is the virtual node, then x is the last available node
		else return false;
	}
	public boolean isValid(int x) {
		return (x==unionFind.find(x));
	}
	
	public static void main(String[] args) {
		SuccessorWithDelete test = new SuccessorWithDelete(10);

		test.remove(2);
		test.remove(1);
		//test.remove(0);
		test.remove(9);
		test.remove(7);
		test.remove(8);
		test.remove(6);
		test.remove(5);
		test.remove(4);
		test.remove(3);
		
		
		for(int i = 0; i<10; i++) {
			System.out.println(i+": "+test.isValid(i));
			if(test.isValid(i)) {
				System.out.println("Successor: "+test.successor(i));
			}
		}
	}

}
