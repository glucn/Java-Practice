import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private final Point[] points;
	private int numberOfSegments;
	private final ArrayList<LineSegment> lineSegments;
	
	public FastCollinearPoints(Point[] points) { // finds all line segments containing 4 or more points
		// check the input argument, handle the corner cases
		if (points == null) throw new IllegalArgumentException("The argument to the constructor is null");
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new IllegalArgumentException("The argument to the constructor contains a null point");
			for (int j = 0; j < i; j++) {
				if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException("The argument to the constructor contains a repeated point");
			}
		}
		
		// start to identify all the line segments
		this.points = new Point[points.length];
		for (int i = 0; i < points.length; i++) this.points[i] = points[i];
		this.numberOfSegments = 0;
		this.lineSegments = new ArrayList<LineSegment>();
		this.sortPoints();
		this.identifyLineSegments();
		
	}
	
	private void sortPoints() { // get all points sorted, make it easy for afterwards processes
		Arrays.sort(points);
	}
	
	private void identifyLineSegments() { // identify all the line segments
		
		for (int i = 0; i < points.length; i++) {
			// calculate the slope from point[i] to other points
			int n = 0;
			double[] slope = new double[this.points.length-1];
			int[] index = new int[this.points.length-1];
			for (int j = 0; j < points.length; j++) {
				if (i == j) continue;
				slope[n] = points[i].slopeTo(points[j]);
				index[n] = j;
				n++;
			}
			
			// sort the slope
			double[] auxSlope = new double[slope.length];
			int[] auxIndex = new int[index.length];
			for (int j = 0; j < slope.length; j++) {
				auxSlope[j] = slope[j];
				auxIndex[j] = index[j];
			}
			sortWithIndex(slope, auxSlope, index, auxIndex, 0, slope.length-1);
			
			// identify line segments
			int j = 0, k = 1;
			while (k < slope.length) {
				if (Double.compare(slope[j], slope[k]) != 0) {
					if (k < j+3) { // less than 3 points with same slope to point[i]
						j = k;
						k++;
						continue;
					} else {
						// new line segment: j -> (k-1)
						if (index[j] < i) { // remove duplicate
							j = k+1;
							k++;
							continue;
						} else {
							int start = i;
							int end = index[k-1];
							this.lineSegments.add(new LineSegment(this.points[start], this.points[end]));
							this.numberOfSegments++;
							j = k;
							k++;
							continue;
						}
					}
				} else {
					if (k == slope.length-1 && k >= j+3) { // new line segment: j -> k
						if (index[j] > i) { // remove duplicate
							k++;
							continue;
						} else {
							int start = i;
							int end = index[k];
							this.lineSegments.add(new LineSegment(this.points[start], this.points[end]));
							this.numberOfSegments++;
							k++;
							continue;
						}
					} else {
						k++;
						continue;
					}
				}
			}			
		}
	}
	
	
	private void sortWithIndex(double[] slope, double[] auxSlope, int[] index, int[] auxIndex, int low, int high) {
		if (high <= low) return;
		int mid = low + (high - low) / 2;
		sortWithIndex(slope, auxSlope, index, auxIndex, low, mid);
		sortWithIndex(slope, auxSlope, index, auxIndex, mid+1, high);
		mergeWithIndex(slope, auxSlope, index, auxIndex, low, mid, high);
	}
	
	private void mergeWithIndex(double[] slope, double[] auxSlope, int[] index, int[] auxIndex, int low, int mid, int high) {
		for (int k = low; k <= high; k++) {
			auxSlope[k] = slope[k];
			auxIndex[k] = index[k];
		}
		
		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++) {
			if (i > mid) {
				slope[k] = auxSlope[j];
				index[k] = auxIndex[j];
				j++;
			}
			else if (j > high) {
				slope[k] = auxSlope[i];
				index[k] = auxIndex[i];
				i++;
			}
			else if (auxSlope[j] < auxSlope[i]) {
				slope[k] = auxSlope[j];
				index[k] = auxIndex[j];
				j++;
			}
			else {
				slope[k] = auxSlope[i];
				index[k] = auxIndex[i];
				i++;
			}
		}
	}
	
	public int numberOfSegments() { // the number of line segments
		return this.numberOfSegments;
	}
	
	public LineSegment[] segments() { // the line segments
		LineSegment[] result = this.lineSegments.toArray(new LineSegment[this.numberOfSegments]);
		return result;
	}
	
	public static void main(String[] args) {
		
		In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
    	
	}

}
