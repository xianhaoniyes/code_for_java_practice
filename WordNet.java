import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.DirectedCycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java. lang.IllegalArgumentException;


public class WordNet {

    private final Digraph digraph;
    private ArrayList<String> id_string = new ArrayList<>();
    private HashMap<String,LinkedList<Integer>> string_id =new HashMap<>();
    private HashSet<String> strings = new HashSet<>(); // when to use a hashtable
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){

        if(synsets==null|| hypernyms==null) throw new IllegalArgumentException("IllegalArgumentException");
        In in = new In(synsets);

        int i = 0;

        while(in.hasNextLine()){
            id_string.add(new String());
            String string = in.readLine();
            String[] field = string.split(",");
            String[] nouns= field[1].split(" ");
            for(int j=0 ; j < nouns.length; j++) {
                id_string.add(new String(field[1]));
                if(string_id.containsKey(nouns[j]))
                    string_id.get(nouns[j]).add(i);
                else {
                        LinkedList<Integer>  ll = new LinkedList<>();
                        ll.add(i);
                        string_id.put(nouns[j],ll);
                        }

                 strings.add(nouns[j]);
            }
            i++;

        }// when to use i and when to use j, be aware.

        int V=i;
        digraph = new Digraph(V);


        in = new In(hypernyms);

        while(in.hasNextLine()){
            String  string= in.readLine();
            String[] field = string.split(",");
            for(int j = 1; j<field.length;j++)
                digraph.addEdge(Integer.parseInt(field[0]),Integer.parseInt(field[j]));

        }

        int root_number=0;
        for(int v=0; v < digraph.V();v++){
            StdOut.printf("%d :%d\n",v,digraph.outdegree(v));
            if(digraph.outdegree(v) == 0)
                root_number++;}

         if (root_number > 1)throw  new IllegalArgumentException("IllegalArgumentException");
        DirectedCycle dc =new DirectedCycle(digraph);
        if(dc.hasCycle())  {  throw  new IllegalArgumentException("IllegalArgumentException");}
        sap = new SAP(digraph);
    }


    public Iterable<String> nouns(){
            return strings;
    }

    public boolean isNoun(String word){
        return strings.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
          int d = 0;
          d = sap.length(string_id.get(nounA),string_id.get(nounB));
          return d;

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
            int an = sap.ancestor(string_id.get(nounA),string_id.get(nounB));
            return id_string.get(an);
    }

    // do unit testing of this class
    public static void main(String[] args){}
}
