import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class SAP {
    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("null input");
        digraph = new Digraph(G);
    }

    private void validate(int v) {
        if (v < 0 || v > digraph.V()) {
            throw new IllegalArgumentException("vertex is not in the graph");
        }
    }

    private void validate(Iterable<Integer> v) {
        if (v == null) throw new IllegalArgumentException("null input");
        for (Integer vv : v) {
            if (vv == null) throw new IllegalArgumentException("null input");
            validate(vv);
        }
    }

    private int[] shortest(int v, int w) {
        validate(v);
        validate(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int length = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < length) {
                    length = len;
                    ancestor = i;
                }
            }
        }

        if (ancestor == -1) return new int[]{-1, -1};
        return new int[]{length, ancestor};

    }

    private int[] shortest(Iterable<Integer> v, Iterable<Integer> w) {
        validate(v);
        validate(w);

        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);

        int length = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                int len = bfsv.distTo(i) + bfsw.distTo(i);
                if (len < length) {
                    length = len;
                    ancestor = i;
                }
            }
        }

        if (ancestor == -1) return new int[]{-1, -1};
        return new int[]{length, ancestor};

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int[] r = shortest(v, w);
        return r[0];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int[] r = shortest(v, w);
        return r[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int[] r = shortest(v, w);
        return r[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int[] r = shortest(v, w);
        return r[1];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        ArrayList<Integer> v = new ArrayList<Integer>(Arrays.asList(0, null, 7, 9, 12));
        ArrayList<Integer> w = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 10));
        int length   = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

    }
}