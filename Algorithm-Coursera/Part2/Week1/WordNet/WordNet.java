import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet {
    private SAP sap;
    private final Map<String, ArrayList<Integer>> noun2id;
    private final Map<Integer, String> id2nouns;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException("null input");

        noun2id = new HashMap<String, ArrayList<Integer>>();
        id2nouns = new HashMap<Integer, String>();

        readSynsets(synsets);
        readHypernyms(hypernyms);

        // StdOut.println(noun2id);
    }

    private void readSynsets(String synsets) {
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] strs = line.split(",");
            if (strs.length < 2) continue;
            int idx = Integer.parseInt(strs[0]);
            id2nouns.put(idx, strs[1]);

            String[] nouns = strs[1].split(" ");
            for (String n : nouns) {
                ArrayList<Integer> ids = noun2id.get(n);
                if (ids != null) {
                    ids.add(idx);
                } else {
                    ArrayList<Integer> newIds = new ArrayList<Integer>();
                    newIds.add(idx);
                    noun2id.put(n, newIds);
                }
            }
        }
        in.close();
    }

    private void readHypernyms(String hypernyms) {
        In in = new In(hypernyms);
        Digraph digraph = new Digraph(noun2id.size());

        while (in.hasNextLine()) {
            String[] ids = in.readLine().split(",");
            if (ids.length < 2) continue;
            int from = Integer.parseInt(ids[0]);
            for (int i = 1; i < ids.length; i++) digraph.addEdge(from, Integer.parseInt(ids[i]));
        }
        in.close();

        DirectedCycle dc = new DirectedCycle(digraph);
        if (dc.hasCycle()) throw new IllegalArgumentException("cycle detected");

        // TODO: fix multiple roots detection, the following code is causing problem
//        boolean foundRoot = false;
//
//        for (int i = 0; i < digraph.V(); i++) {
//            if (digraph.outdegree(i) == 0) {
//                if (foundRoot) {
//                    throw new IllegalArgumentException("multiple root detected");
//                }
//                foundRoot = true;
//            }
//        }

        sap = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return noun2id.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("null input");
        return noun2id.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException("null input");
        if (!this.isNoun(nounA) || !this.isNoun(nounB)) throw new IllegalArgumentException("not a WordNet noun");
        return sap.length(noun2id.get(nounA), noun2id.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException("null input");
        if (!this.isNoun(nounA) || !this.isNoun(nounB)) throw new IllegalArgumentException("not a WordNet noun");

        int ancestor = sap.ancestor(noun2id.get(nounA), noun2id.get(nounB));
        return id2nouns.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        StdOut.println(wordNet.isNoun("a"));
        StdOut.println(wordNet.distance("a", "b"));
    }
}
