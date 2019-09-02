import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet w;
    public Outcast(WordNet wordnet){
        w= wordnet;
    }


    public String outcast(String[] nouns){
        int d_max = Integer.MIN_VALUE;
        String outcast_string = null;
        int d;
        for(String s: nouns){

            d = total_distance(s,nouns);
             if(d > d_max) {
                 d_max = d;
                 outcast_string = s;
             }
        }
        return outcast_string;
    }

    private int total_distance(String noun,String[] nouns){
        int d = 0;
        for(String s:nouns){
            d+= w.distance(noun,s);
        }
        return d;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
