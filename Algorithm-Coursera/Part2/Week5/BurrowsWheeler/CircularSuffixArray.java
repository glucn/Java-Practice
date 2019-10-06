import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
  private final String s;
  private final Integer[] index;

  // circular suffix array of s
  public CircularSuffixArray(String s) {
    if (s == null) throw new IllegalArgumentException("null input");

    this.s = s;
    index = new Integer[s.length()];
    for (int i = 0; i < s.length(); i++) {
      index[i] = i;
    }
    Arrays.sort(index, suffixOrder());
  }

  private Comparator<Integer> suffixOrder() {
    return new SuffixOrder();
  }

  private class SuffixOrder implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      int len = s.length();

      for (int i = 0; i < len; i++) {
        char a = s.charAt((o1 + i) % len);
        char b = s.charAt((o2 + i) % len);
        if (a < b) {
          return -1;
        } else if (a > b) {
          return 1;
        }
      }

      return 0;
    }
  }

  // length of s
  public int length() {
    return s.length();
  }

  // returns index of ith sorted suffix
  public int index(int i) {
    if (i < 0 || i >= s.length()) throw new IllegalArgumentException("illegal index");
    return index[i];
  }

  // unit testing (required)
  public static void main(String[] args) {
    CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
    for (int i = 0; i < csa.length(); ++i) {
      StdOut.print(csa.index(i) + " ");
    }
    StdOut.println();
  }

}
