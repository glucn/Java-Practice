import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] threshold;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("the size of grid must be greater than 0");
        }
        this.trials = trials;
        this.threshold = new double[trials];
        this.mean = 0.0;
        this.stddev = 0.0;
        
        for (int i = 0; i < trials; ++i) {
            Percolation per = new Percolation(n);
            // System.out.println("Start of interation "+(i+1));
            while (!per.percolates()) {
                int row, col;
                do {
                    row = StdRandom.uniform(1, n+1);
                    col = StdRandom.uniform(1, n+1);
                } while (per.isOpen(row, col));
                // System.out.println("Row: "+row + " Col: "+col);
                per.open(row, col);
            }
            threshold[i] = (double) per.numberOfOpenSites() / (double) (n*n);
            // System.out.println("Iteration "+(i+1)+": OpenSites: "+per.numberOfOpenSites()+" Threshold: "+threshold[i]);
        }
    }
    
    public double mean() { // sample mean of percolation threshold
        this.mean = StdStats.mean(threshold);
        return (this.mean);
    }
    
    public double stddev() { // sample standard deviation of percolation threshold
        this.stddev = StdStats.stddev(threshold);
        return (this.stddev);
    }
    
    public double confidenceLo() { // low  endpoint of 95% confidence interval
        // double mean = StdStats.mean(threshold);
        // double stddev = StdStats.stddev(threshold);
        if (this.mean == 0.0) this.mean = StdStats.mean(threshold);
        if (this.stddev == 0.0) this.stddev = StdStats.stddev(threshold);
        return (mean - CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }
    public double confidenceHi() { // high endpoint of 95% confidence interval
        // double mean = StdStats.mean(threshold);
        // double stddev = StdStats.stddev(threshold);
        if (this.mean == 0.0) this.mean = StdStats.mean(threshold);
        if (this.stddev == 0.0) this.stddev = StdStats.stddev(threshold);
        return (mean + CONFIDENCE_95 * stddev / Math.sqrt(trials));
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("invalid parameter");
            return;
        }
        PercolationStats perStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + perStats.mean());
        System.out.println("stddev                  = " + perStats.stddev());
        System.out.println("95% confidence interval = ["+perStats.confidenceLo()+", "+perStats.confidenceHi()+"]");
    }
}
