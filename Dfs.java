import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;

public class Dfs {

    private int start_point;
    private int[] marked;
    private int[] pathto;
    private HashSet<String> hashset;
    private Mod_tst<Integer> tst;
    private Graph board_graph;
    private char[] words_array;

    public Dfs(int amount, int start_point,Mod_tst<Integer> tst, Graph board_graph, char[] words_array, HashSet<String> hashset ){
        marked = new int[amount];
        pathto = new int[amount];
        this.start_point =start_point;
        this.tst =tst;
        this.board_graph = board_graph;
        this.words_array = words_array;
        this.hashset = hashset;
    }

    public void dfs(){
        d_fs(start_point,hashset);
    }

    private void d_fs(int v, HashSet<String> hashset ){
        Stack<Integer> cha_stack = new Stack<>();
        marked[v] = 1;
        for(int j = v; j!=start_point; j = pathto[j]) {
            cha_stack.push(j);
        }

        cha_stack.push(start_point);

        StringBuilder string = new StringBuilder();
        while(!cha_stack.isEmpty()) {
            char c = words_array[cha_stack.pop()];
            if (c!='Q')
                string.append(c);
            else{
                string.append(c);
                string.append('U');
            }
        }
        if (string.length()>2){
            String s = string.toString();

            int t= tst.containPrefix(s);
            if(t==0)
            {
                marked[v]=0;
                return;
            }
            if(t==2)
                hashset.add(s);
        }

        for(int w :board_graph.adj(v)){

            if(marked[w]==0){
                pathto[w] = v;
                d_fs(w,hashset);


            }
        }
        marked[v]=0;
    }
}
