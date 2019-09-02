
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import java.util.HashSet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {

    private Mod_tst<Integer> tst;

    public BoggleSolver(String[] dictionary){

        tst = new Mod_tst<>();
        for (String i: dictionary) {
            tst.put(i, 1);
        }

    }


    public Iterable<String> getAllValidWords(BoggleBoard board){

        int m = board.rows();
        int n = board.cols();
        int amount = m*n;
        char[] words_array = new char[amount];
        HashSet<String> hashset= new HashSet<>();
        for(int m_i = 0; m_i < m; m_i++)
            for(int n_i = 0 ; n_i < n;n_i++)
                words_array[m_i*n+n_i]=board.getLetter(m_i, n_i);

        Graph board_graph = new Graph(amount);
        create_edge(board_graph,m,n);
        for(int i = 0; i < amount;i++){
            //StdOut.println(i);
            Dfs dfs = new Dfs(board_graph.V(),i,tst,board_graph,words_array,hashset);
            dfs.dfs();
        }

        return hashset;
    }


    //check this part carefully
    private  void create_edge(Graph board_graph,int m, int n){
        for(int m_i = 0; m_i < m-1; m_i++)
            for(int n_i = 0; n_i<n-1;n_i++){
                int a = m_i*n + n_i;
                board_graph.addEdge(a,a+1);
                board_graph.addEdge(a,a+n);
                board_graph.addEdge(a,a+n+1);
                board_graph.addEdge(a+1,a+n);
            }

        for(int n_i = 0; n_i<n-1;n_i++){
            int a = (m-1)*n+n_i;
            board_graph.addEdge(a,a+1);
        }

        for(int m_i = 0; m_i<m-1;m_i++){
            int a = m_i*n + n-1;
            board_graph.addEdge(a,a+n);
        }
    }


    public int scoreOf(String word){
            int t = tst.containPrefix(word);
            if (t==0||t==1)
                return 0;
            int l =word.length();
            if(l<3)
                return 0;
            if(l<5)
                return 1;
            if(l==5)
                return 2;
            if(l==6)
                return 3;
            if(l==7)
                return 5;

            return 11;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }

}