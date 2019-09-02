import edu.princeton.cs.algs4.StdOut;

public class
CircularSuffixArray {

    private CircularString string;
    private int length;
    private int[] index;

    private class CircularString {

        private char[] c_string;
        private int length;

        public CircularString(String s){
            this.length = s.length();
            c_string = new char[length];
            for (int i = 0; i<length;i++)
                c_string[i]=s.charAt(i);
        }

        public char charAt(int i){
            return c_string[i%length];
        }

        public int len(){
            return this.length;
        }


    }

    public CircularSuffixArray(String s){
        if(s==null)
            throw new IllegalArgumentException();
        string = new CircularString(s);
        this.length = s.length();
        this.index = sort();

    }

    private int[] sort(){

        int R = 256;
        int N = length;
        int[] index = new int[N];
        for(int i = 0; i < N ; i++)
            index[i]=i;

        int[] aux = new int[N];

        for (int d = N-1; d>=0; d--){

            int[] count = new int[R+1];
            for (int i =0; i < N; i++)
                count[string.charAt(index[i]+d) +1]++;
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];
            for(int i = 0; i < N;i++)
                aux[count[string.charAt(index[i]+d)]++]=index[i];
            for(int i = 0; i < N;i++)
                index[i]=aux[i];
        }
        return index;
    }

    public int length(){
        return length;
    }
    public int index(int i){
        if(i<0||i>length-1)
            throw new IllegalArgumentException("IllegalArgumentException");
        return index[i];
    }

    public static void main(String[] args){

        CircularSuffixArray c_string = new CircularSuffixArray(args[0]);
        for(int i = 0; i < c_string.length;i++)
            StdOut.printf("%d ", c_string.index[i]);
    }
}
