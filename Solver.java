import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;


import java.lang.IllegalArgumentException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Solver {
    private MinPQ<SearchNode> minPQ;
    private boolean solvable = false;
    private int path_moves = 0;
    private Path_solution path_solution = new Path_solution();



    private class SearchNode{
        private Board board;
        private int moves;
        private SearchNode predecessor ;
        private int priority_manhattan;
       // private int priority_hamming;

    }

    private class ManhattanComparator implements Comparator<SearchNode> {
        public int compare(SearchNode s1, SearchNode s2){
            if(s1.priority_manhattan < s2.priority_manhattan) return -1;
             else if (s1.priority_manhattan > s2.priority_manhattan) return 1;
                else return 0;
         //    else if(s1.priority_hamming < s2.priority_hamming) return -1;
           //  else if(s1.priority_hamming < s2.priority_hamming) return 1;
           //  else return 0;
        }
    }

    public Solver(Board initial){

        if (initial == null) throw new IllegalArgumentException("IllegalArgumentException");

        ManhattanComparator manhattanComparator = new ManhattanComparator();

        minPQ = new MinPQ<>(manhattanComparator);
        SearchNode searchNode = new SearchNode();
        searchNode.board = initial;
        searchNode.moves = 0;
        searchNode.predecessor = null;
        searchNode.priority_manhattan = initial.manhattan();
       // searchNode.priority_hamming = initial.hamming();
        minPQ.insert(searchNode);

        MinPQ<SearchNode> minPQ_t = new MinPQ<>(manhattanComparator);
        SearchNode searchNode_t = new SearchNode();
        searchNode_t.board = initial.twin();
        searchNode_t.moves = 0;
        searchNode_t.predecessor = null;
        searchNode_t.priority_manhattan = initial.manhattan();
       // StdOut.print(searchNode_t.board);
       // if(true) throw new IllegalArgumentException("jiayou");
       // searchNode_t.priority_hamming = initial.hamming();
        minPQ_t.insert(searchNode_t);
        int i=0;
        SearchNode searchNode_s;
        while(true){

            searchNode = minPQ.delMin();


            searchNode_t = minPQ_t.delMin();

           /* if(i<100){
                StdOut.println(searchNode_t.board);
                i++;
            }
            else throw new IllegalArgumentException("jiayou");*/
            if(searchNode.board.isGoal()||searchNode_t.board.isGoal()) break;

            for(Board b: searchNode.board.neighbors()){
                if (searchNode.predecessor!=null&&searchNode.predecessor.board.equals(b)) continue;
                searchNode_s = new SearchNode();
                searchNode_s.board = b;
                searchNode_s.moves = searchNode.moves + 1;
                searchNode_s.predecessor = searchNode;
                searchNode_s.priority_manhattan = b.manhattan()+searchNode_s.moves;
            //    searchNode_s.priority_hamming = b.hamming()+ searchNode_s.moves;
                minPQ.insert(searchNode_s);

            }

            for(Board b: searchNode_t.board.neighbors()){
                if (searchNode.predecessor!=null&&searchNode_t.predecessor.board.equals(b)) { continue;}
                searchNode_s = new SearchNode();
                searchNode_s.board = b;
                searchNode_s.moves = searchNode_t.moves + 1;
                searchNode_s.predecessor = searchNode_t;
                searchNode_s.priority_manhattan = b.manhattan() + searchNode_s.moves;
             //   searchNode_s.priority_hamming = b.manhattan() + searchNode_s.moves;
                minPQ_t.insert(searchNode_s);
            }
        //    StdOut.printf("haven't finished %d\n", path_moves);
        }

        if(searchNode.board.isGoal()) {
            solvable = true;
            path_moves = searchNode.moves;
            while(searchNode!=null)
            {path_solution.add(searchNode.board); searchNode = searchNode.predecessor;}
        }
            else solvable = false;



    }

    public boolean isSolvable(){
            return solvable == true;
    }


    public int moves(){
        if(solvable == false)
            return -1;
        else return path_moves;}

    public Iterable<Board> solution(){
        if(solvable == false) return null;
            else return  path_solution;
    }

    private class Path_solution implements Iterable<Board>{


        private Node first = null;

        private class Node{
            Board board;
            Node next=null;
        }

        public void add(Board b){

            Node old_first = first; first = new Node(); first.board = b; first.next =old_first;
        }


        public Iterator<Board> iterator() {
            return new Path_solution_iterator();
        }

        private class Path_solution_iterator implements Iterator<Board>{
            private Node current = first;

            public boolean hasNext(){
                return current != null;
            }

            public Board next() {
                if(!hasNext()) throw new NoSuchElementException();
                Board i=current.board;
                current =current.next;
                return i;
            }
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}