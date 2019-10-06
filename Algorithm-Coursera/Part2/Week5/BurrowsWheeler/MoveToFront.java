import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {

  private static LinkedList<Character> createList() {
    LinkedList<Character> list = new LinkedList<>();
    for (int i = 0; i < 256; i++) {
      list.add((char) i);
    }
    return list;
  }

  // apply move-to-front encoding, reading from standard input and writing to standard output
  public static void encode() {
    LinkedList<Character> list = createList();
    while (!BinaryStdIn.isEmpty()) {
      char c = BinaryStdIn.readChar();
      int index = list.indexOf(c);
      list.remove(index);
      list.addFirst(c);
      BinaryStdOut.write(index, 8);
    }
    BinaryStdOut.close();
  }

  // apply move-to-front decoding, reading from standard input and writing to standard output
  public static void decode() {
    LinkedList<Character> list = createList();
    while (!BinaryStdIn.isEmpty()) {
      int index = BinaryStdIn.readChar();
      char c = list.get(index);
      list.remove(index);
      list.addFirst(c);
      BinaryStdOut.write(c, 8);
    }
    BinaryStdOut.close();
  }

  // if args[0] is "-", apply move-to-front encoding
  // if args[0] is "+", apply move-to-front decoding
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Illegal argument");
    }
    if (args[0].equals("-")) {
      encode();
    }
    else if (args[0].equals("+")) {
      decode();
    }
    else {
      throw new IllegalArgumentException("Illegal argument");
    }
  }

}