import edu.princeton.cs.algs4.*;

public class Percolation {

	private WeightedQuickUnionUF unionFind;
	private boolean[] isSiteOpen;
	private int maxNode;
	private int size;
	private int openSites;
	
	public Percolation(int n) { // create n-by-n grid, with all sites blocked
		if(n<=0) {
			throw new IllegalArgumentException("the size of grid must be greater than 0");
		}
		size = n;
		maxNode = n*n+1;  //node number from 0 to n*n+1
		openSites = 0;
		unionFind = new WeightedQuickUnionUF(maxNode+1);
		isSiteOpen = new boolean[maxNode+1];
		
		for(int i=1; i<n+1; ++i) {
			unionFind.union(0, i);
			unionFind.union(maxNode, maxNode-i);
		}
	}
	
	private int convert(int row, int col) { //convert (row, col) to the index in 0...n*n+1
		return (row-1)*size+col;
	}
	
	public void open(int row, int col) { // open site (row, col) if it is not open already
		if (row<1 || row>size || col<1 || col>size) {
			throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
		}
		
		if (isOpen(row, col)) return;
		if (row>1 && isOpen(row-1,col)) unionFind.union(convert(row, col), convert(row-1, col));
		if (row<size && isOpen(row+1,col)) unionFind.union(convert(row, col), convert(row+1, col));
		if (col>1 && isOpen(row, col-1)) unionFind.union(convert(row, col), convert(row, col-1));
		if (col<size && isOpen(row, col+1)) unionFind.union(convert(row, col), convert(row, col+1));
		 
		++openSites;
	}
	 
	public boolean isOpen(int row, int col) { // is site (row, col) open?
		if (row<1 || row>size || col<1 || col>size) {
			throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
		}
		return isSiteOpen[convert(row, col)];
	}
	 
	public boolean isFull(int row, int col) { // is site (row, col) full?
		if (row<1 || row>size || col<1 || col>size) {
			throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
		}
		return unionFind.connected(0, convert(row, col));
	}
	 
	public int numberOfOpenSites() { // number of open sites
		 return openSites;
	}
	 
	public boolean percolates() { // does the system percolate?
		 return unionFind.connected(0, maxNode);
	}
	   

	public static void main(String[] args) {
		

	}

}
