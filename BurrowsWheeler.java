
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Huffman;

public class BurrowsWheeler {

    public static void transform(){

        String s = BinaryStdIn.readString();
        int l = s.length();
        CircularSuffixArray  csa = new CircularSuffixArray(s);
        StringBuilder c_string = new StringBuilder();
        for(int i = 0; i < l;i++){
            int seq = csa.index(i);
            c_string.append(s.charAt((seq+l-1)%l));
            if(seq==0)
                BinaryStdOut.write(i);
        }

        BinaryStdOut.write(c_string.toString());
        BinaryStdOut.close();
    }


    public static void inverseTransform(){

        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        int N = s.length();
        int R = 256;
        int count[] = new int[R+1];
        char[] t = s.toCharArray();
        int[] next = new int[N];
        char[] aux = new char[N];


        for(int i = 0; i < N; i++)
            count[t[i]+1]++;

        for(int r = 0; r < R; r++)
            count[r+1] +=count[r];

        for(int i = 0; i < N; i++){
            aux[count[t[i]]] = t[i];    // the problem is this part, notice here
            next[count[t[i]]++] = i;

        }

        char[] original = new char[N];
        for(int i = 0, current = first; i < N; i++,current= next[current]) {
            original[i] = aux[current];
        }

        String os= new String(original);
        BinaryStdOut.write(os);
        BinaryStdOut.close();
    }

    public static void main(String[] args){

        if(args[0].equals("-")) {
            transform();
        }

        if(args[0].equals("+")){
            inverseTransform();
        }


    }
}
