import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.HashMap;

public class BurrowsWheeler {

  // apply Burrows-Wheeler transform,
  // reading from standard input and writing to standard output
  public static void transform() {
    String s = BinaryStdIn.readString();
    CircularSuffixArray csa = new CircularSuffixArray(s);
    int len = s.length();

    // output `first`
    for (int i = 0; i < len; i++) {
      if (csa.index(i) == 0) {
        BinaryStdOut.write(i);
        break;
      }
    }

    // output `t[]`
    for (int i = 0; i < len; i++) {
      int index = csa.index(i);
      char c;
      if (index == 0) {
        c = s.charAt(len-1);
      } else {
        c = s.charAt(index-1);
      }
      BinaryStdOut.write(c, 8);
    }

    BinaryStdOut.close();
  }

  // apply Burrows-Wheeler inverse transform,
  // reading from standard input and writing to standard output
  public static void inverseTransform() {
    int first = BinaryStdIn.readInt();
    String tt = BinaryStdIn.readString();
    char[] t = tt.toCharArray();

    HashMap<Character, Queue<Integer>> m = new HashMap<>();

    for (int i = 0; i < t.length; i++) {
      if (!m.containsKey(t[i])) {
        m.put(t[i], new Queue<>());
      }
      m.get(t[i]).enqueue(i);
    }

    Arrays.sort(t); // TODO: this might be introducing performance problem

    int[] next = new int[t.length];

    for (int i = 0; i < t.length; i++) {
      next[i] = m.get(t[i]).dequeue();
    }

    for (int i = 0; i < t.length; i++) {
      BinaryStdOut.write(t[first], 8);
      first = next[first];
    }
    BinaryStdOut.close();
  }

  // if args[0] is "-", apply Burrows-Wheeler transform
  // if args[0] is "+", apply Burrows-Wheeler inverse transform
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Illegal argument");
    }
    if (args[0].equals("-")) {
      transform();
    }
    else if (args[0].equals("+")) {
      inverseTransform();
    }
    else {
      throw new IllegalArgumentException("Illegal argument");
    }
  }

}
