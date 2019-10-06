import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.io.*;
// import java.util.Scanner;

public class Percolation {

    private final WeightedQuickUnionUF unionFind;
    private boolean[] isSiteOpen;
    private boolean[] connectTop;
    private boolean[] connectBottom;
    private final int totalSite;
    private final int size;
    private int openSites;
    private boolean isPercolates;
    
    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("the size of grid must be greater than 0");
        }
        size = n;
        totalSite = n*n;  // size index from 0 to n*n-1
        openSites = 0;
        isPercolates = false;
        unionFind = new WeightedQuickUnionUF(totalSite);
        isSiteOpen = new boolean[totalSite];
        connectTop = new boolean[totalSite];
        connectBottom = new boolean[totalSite];
        for (int i = 0; i < n; ++i) {
            connectTop[i] = true;
            connectBottom[totalSite-i-1] = true;
        }
    }
    
    private int convert(int row, int col) { // convert (row, col) to the index in 0...n*n-1
        return (row-1)*size+col-1;
    }
    
    public void open(int row, int col) { // open site (row, col) if it is not open already
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
        }
        int index = convert(row, col);
        if (isSiteOpen[index]) return;
        
        isSiteOpen[index] = true;
        boolean topUp = false;
        boolean topDown = false;
        boolean topLeft = false;
        boolean topRight = false;
        boolean bottomUp = false;
        boolean bottomDown = false;
        boolean bottomLeft = false;
        boolean bottomRight = false;
        
        if (row > 1 && isSiteOpen[index-size]) {  // up
            int indexUpParent = unionFind.find(index-size);
            topUp = connectTop[indexUpParent];
            bottomUp = connectBottom[indexUpParent];
            unionFind.union(index, index-size);
        }
        if (row < size && isSiteOpen[index+size]) { // down
            int indexDownParent = unionFind.find(index+size);
            topDown = connectTop[indexDownParent];
            bottomDown = connectBottom[indexDownParent];
            unionFind.union(index, index+size);
        }
        if (col > 1 && isSiteOpen[index-1]) { // left
            int indexLeftParent = unionFind.find(index-1);
            topLeft = connectTop[indexLeftParent];
            bottomLeft = connectBottom[indexLeftParent];
            unionFind.union(index, index-1);
        }
        if (col < size && isSiteOpen[index+1]) { // right
            int indexRightParent = unionFind.find(index+1);
            topRight = connectTop[indexRightParent];
            bottomRight = connectBottom[indexRightParent];
            unionFind.union(index, index+1); 
        }
        ++openSites;
        int indexParent = unionFind.find(index);
        connectTop[indexParent] = connectTop[indexParent] | connectTop[index] | topUp | topDown | topLeft | topRight;
        connectBottom[indexParent] = connectBottom[indexParent] | connectBottom[index] | bottomUp | bottomDown | bottomLeft | bottomRight;
        if (connectTop[indexParent] && connectBottom[indexParent]) {
            isPercolates = true;
        }
    }
    
    public boolean isOpen(int row, int col) { // is site (row, col) open?
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
        }
        return isSiteOpen[convert(row, col)];
    }
     
    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row "+row+ ", col "+ col + " is not in the grid.");
        }
        int index = convert(row, col);
        return (connectTop[unionFind.find(index)] & isSiteOpen[index]);
    }
     
    public int numberOfOpenSites() { // number of open sites
         return openSites;
    }
     
    public boolean percolates() { // does the system percolate?
         return this.isPercolates;
    }
       
    public static void main(String[] args) {
        /*
        try {
            Scanner scan = new Scanner(new File("input20.txt"));
            int n = scan.nextInt();
            System.out.println("n = "+n);
            Percolation per = new Percolation(n);
            while (scan.hasNext()) {
                int row = scan.nextInt();
                int col = scan.nextInt();
                System.out.println("row = " + row + ", col = " + col);
                per.open(row, col);
                
                System.out.println("Opensite: " + per.numberOfOpenSites());
                if(per.percolates()) System.out.println("The grid percolates!");
                for(int i = 0; i < n; ++i) {
                    for(int j = 0; j < n; ++j) {
                        if(per.isFull(i+1, j+1))
                            System.out.print("# ");
                        else if(per.isOpen(i+1, j+1))
                            System.out.print("O ");
                        else
                            System.out.print("X ");
                    }
                    System.out.print("\n");
                }
            }
            scan.close();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        */
    }

}
