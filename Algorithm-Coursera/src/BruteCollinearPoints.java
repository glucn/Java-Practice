import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private final Point[] points;
	private final ArrayList<LineSegment> lineSegments;
	private int numberOfSegments;
	
	public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
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
			Point p0 = points[i];
			for (int j = 0; j < i; j++) {
				Point p1 = points[j];
				for (int k = 0; k < j; k++) {
					Point p2 = points[k];
					if (Double.compare(p0.slopeTo(p1), p0.slopeTo(p2)) != 0) continue;
					for (int m = 0; m < k; m++) {
						Point p3 = points[m];
						if (Double.compare(p0.slopeTo(p1), p0.slopeTo(p3)) != 0) continue;
						this.lineSegments.add(new LineSegment(p0, p3));
						this.numberOfSegments++;
					}
				}
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
		/*
		Point[] p = new Point[4];
		p[0] = new Point(0, 0);
    	p[1] = new Point(0, 1);
    	p[2] = new Point(0, 2);
    	p[3] = new Point(0, 3);

    	BruteCollinearPoints bcp = new BruteCollinearPoints(p);
    	
    	System.out.println(bcp.toString());
    	System.out.println(bcp.numberOfSegments());
    	LineSegment[] ls = bcp.segments();
    	for (LineSegment seg: ls) { System.out.println(seg.toString()); }
    	*/
	}

}
