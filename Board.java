import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.lang.Math;
import java.util.NoSuchElementException;

public class Board {

    private int[] blocks;
    private int N;

    public Board(int[][] blocks){
        N = blocks.length;
        this.blocks = new int[N*N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                this.blocks[i*N+j] = blocks[i][j];

    }
    private Board(){}


    public int dimension(){
        return N;
    }

    public int hamming(){
        int hamming = 0;
        for(int i = 0 ; i < N*N;i++)
            if((blocks[i] != 0) && (blocks[i] != i+1 ))
                hamming++;
        return hamming;
    }

    public int manhattan(){
        int manhattan = 0;
        for(int i = 0 ; i < N*N; i++)
            if(blocks[i] != 0 && (blocks[i] != i+1))
                manhattan =manhattan+ Math.abs((blocks[i]-1)/N - i/N) + Math.abs((blocks[i]-1)%N - i%N) ;


        return manhattan;
    }

    public boolean isGoal(){
        for(int i = 0; i < N*N-1;i++)
            if(blocks[i]!=i+1)
                return false;
        return true;
    }

    public Board twin(){


        Board twin = new Board();
        twin.N =this.N ;
        twin.blocks = new int[N*N];
        for(int i =0 ; i < N*N; i++)
            twin.blocks[i] = this.blocks[i];
        int swap;
        if(twin.blocks[0]!=0&&twin.blocks[1]!=0)
         {swap = twin.blocks[0]; twin.blocks[0] = twin.blocks[1]; twin.blocks[1] =swap;}
         else {swap = twin.blocks[2]; twin.blocks[2] = twin.blocks[3]; twin.blocks[3] =swap;}

         return  twin;
    }


    public boolean equals(Object y){
        if(y==null) throw new IllegalArgumentException("IllegalArgumentException");
        Board y_m = (Board)y;
        for(int i =0; i < N*N; i++ )
            if(this.blocks[i] !=  y_m.blocks[i])
                return false;
        return true;
    }

    public Iterable<Board> neighbors(){
            return new neighbors();
    }

    private class neighbors implements Iterable<Board>{     //private or public, should be private, it is just like using public
                                                          // method to get access to private class.

        private Node first = null;

        private class Node{
            Board board;
            Node next = null;
        }

        public neighbors(){
            int p;
            for(int i = 0; i < N*N; i++)
                if(blocks[i] == 0){
                    p = i-1;
                    if(p>=0 && p/N  == i/N) Add_neighbor(i,p);
                    p = i+1;
                    if(p<N*N && p/N == i/N) Add_neighbor(i,p);
                    p = i -N;
                    if(p >= 0) Add_neighbor(i,p);
                    p = i + N;
                    if (p < N*N) Add_neighbor(i,p);
                }
        }


        private void Add_neighbor(int i ,int p){
            Board board = new Board();
            board.N = N;
            board.blocks = new int[N*N];
            for (int j = 0 ;j < N * N; j++)
                board.blocks[j] = blocks[j];
            int swap;
            swap = board.blocks[i];
            board.blocks[i] =board.blocks[p];
            board.blocks[p] = swap;
           // Swap(board.blocks[i],board.blocks[p]);
            Node old_first = first;
            first = new Node();
            first.board = board;
            first.next = old_first;
        }

        public Iterator<Board> iterator(){
            return  new NeighborIterator();
        }

        private class NeighborIterator implements Iterator<Board>{
                private Node current;
                public NeighborIterator(){current = first;}
                public boolean hasNext() { return current != null; }
                public Board next(){
                        if(!hasNext()) throw new NoSuchElementException();
                        Board i=current.board;
                        current =current.next;
                        return i;
                }
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N*N; i++) {

                s.append(String.format("%2d ", blocks[i]));

            if ((i+1)%N ==0) s.append("\n");
        }
        return s.toString();
    }


}
