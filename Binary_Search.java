import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Binary_Search {
    public static int rank(int k, int[] a ){
        int head = 0;
        int tail = a.length - 1;
        int mid;
        while(head<=tail){
            mid = (head+tail)/2;
            if (k == a[mid]) return mid;
            else if (k<a[mid]) tail= mid -1 ;
            else if (k>a[mid]) head =mid +1;
        }
        return -1;
    }

    public static void main(String args[]){
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if(rank(key,whitelist)<0)
                StdOut.println(key);
        }
    }
}
