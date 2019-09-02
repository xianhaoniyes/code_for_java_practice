import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;


import java.util.ArrayList;

public class SAP {

    private Digraph g;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G){
        g = new Digraph(G);
    }


    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        int min_length =Integer.MAX_VALUE;
        int current;
        ArrayList<Integer> candidates = new ArrayList<>();
        BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(g,v);
        BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(g,w);
        for(int i = 0; i < g.V();i++){
            if(bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i))
            {candidates.add(i);}
        }
        for(int i: candidates){
            current = bfs_v.distTo(i)+bfs_w.distTo(i);
            if(current < min_length)
                min_length = current;
        }

        if (min_length == Integer.MAX_VALUE ) return -1;
        else return min_length;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
        int min_length =Integer.MAX_VALUE;
        int current;
        int an = -1; //this default value may never be used;
        ArrayList<Integer> candidates = new ArrayList<>();
        BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(g,v);
        BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(g,w);
        for(int i = 0; i < g.V();i++){
            if(bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i))
            {candidates.add(i);}
        }
        for(int i: candidates){
            current = bfs_v.distTo(i)+bfs_w.distTo(i);
            if(current < min_length) {
                min_length = current;
                an = i;
            }
        }
        return an;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        if(v==null||w==null) throw new IllegalArgumentException();
        for(Integer i:v){
            if(i==null) throw new IllegalArgumentException();
        }

        for(Integer i:w){
            if(i==null) throw new IllegalArgumentException();
        }

        int min_length =Integer.MAX_VALUE;
        int current;
        ArrayList<Integer> candidates = new ArrayList<>();
        BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(g,v);
        BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(g,w);
        for(int i = 0; i < g.V();i++){
            if(bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i))
            {candidates.add(i);}
        }
        for(int i: candidates){
            current = bfs_v.distTo(i)+bfs_w.distTo(i);
            if(current < min_length)
                min_length = current;
        }

        if(min_length==Integer.MAX_VALUE) return -1;
        else return min_length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        if(v==null||w==null) throw new IllegalArgumentException();
        for(Integer i:v){
            if(i==null) throw new IllegalArgumentException();
        }
        for(Integer i:v){
            if(i==null) throw new IllegalArgumentException();
        }

        for(Integer i:w){
            if(i==null) throw new IllegalArgumentException();
        }
        int min_length =Integer.MAX_VALUE;
        int current;
        int an = -1; //this default value may never be used;
        ArrayList<Integer> candidates = new ArrayList<>();
        BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(g,v);
        BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(g,w);
        for(int i = 0; i < g.V();i++){
            if(bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i))
            {candidates.add(i);}
        }
        for(int i: candidates){
            current = bfs_v.distTo(i)+bfs_w.distTo(i);
            if(current < min_length) {
                min_length = current;
                an = i;
            }
        }
         return an;

    }

    // do unit testing of this class
    public static void main(String[] args){}
}

// the method that you think is quite good actually have some bugs.