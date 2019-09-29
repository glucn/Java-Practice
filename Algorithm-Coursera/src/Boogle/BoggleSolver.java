import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;


public class BoggleSolver
{
  private static int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
  private static class TrieNode {
    TrieNode[] next = new TrieNode[26];
    boolean isWord = false;
  }

  private final TrieNode root;

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    root = new TrieNode();
    for (String s: dictionary) {
      insert(s);
    }
  }

  private void insert(String s) {
    TrieNode n = root;
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'A';
      if (n.next[idx] == null) {
        n.next[idx] = new TrieNode();
      }
      n = n.next[idx];
    }
    n.isWord = true;
  }

  private boolean isInTrie(String s) {
    TrieNode n = root;
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'A';
      if (n.next[idx] == null) {
        return false;
      }
      n = n.next[idx];
    }
    return n.isWord;
  }


  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    int cols = board.cols();
    int rows = board.rows();

    boolean[][] visited = new boolean[rows][cols];
    HashSet<String> validWords = new HashSet<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        dfs(board, i, j, visited, validWords, "", root);
      }
    }

    return validWords;
  }

  private void dfs(BoggleBoard board, int x, int y, boolean[][] visited, HashSet<String> words, String path, TrieNode node) {

    char c = board.getLetter(x, y);
    if (c != 'Q') {
      int idx = c - 'A';
      if (node.next[idx] == null) {
        return;
      }
      node = node.next[idx];
      path = path + c;
    } else {
      if (node.next['Q' - 'A'] == null || node.next['Q' - 'A'].next['U' - 'A'] == null) {
        return;
      }
      node = node.next['Q' - 'A'].next['U' - 'A'];
      path = path + "QU";
    }

    if (node.isWord && path.length() > 2) {
      words.add(path);
    }

    visited[x][y] = true;
    for (int[] m : moves) {
      int xx = x + m[0];
      int yy = y + m[1];
      if (xx >= 0 && xx < board.rows() && yy >= 0 && yy < board.cols() && !visited[xx][yy]) {
        dfs(board, xx, yy, visited, words, path, node);
      }
    }
    visited[x][y] = false;
  }


  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    if (!isInTrie(word)) {
      return 0;
    }

    return getScore(word);
  }

  private int getScore(String word) {
    int length = word.length();
    switch (length) {
      case 0:
      case 1:
      case 2:
        return 0;
      case 3:
      case 4:
        return 1;
      case 5:
        return 2;
      case 6:
        return 3;
      case 7:
        return 5;
      default:
        return 11;
    }
  }

  public static void main(String[] args) {
    In in = new In("dictionary-algs4.txt");
    String[] dictionary = in.readAllStrings();
    BoggleSolver solver = new BoggleSolver(dictionary);
    BoggleBoard board = new BoggleBoard("board-q.txt");
    int score = 0;
    for (String word : solver.getAllValidWords(board)) {
      StdOut.println(word);
      score += solver.scoreOf(word);
    }
    StdOut.println("Score = " + score);
  }
}

