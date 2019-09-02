import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {



    public static void encode(){
        int R = 256;
        char[] list = new char[256];
        for(int i = 0; i<R; i++)
            list[i]=(char)i;

        while(!BinaryStdIn.isEmpty()){
            char c = BinaryStdIn.readChar();
            int i = 0;
            while(c!=list[i])
                i++;

            BinaryStdOut.write(i,8);

            char ime = list[i];
            System.arraycopy(list,0,list,1,i);
            list[0] = ime;


            }
        BinaryStdOut.close();

        }



    public static void decode(){
        int R = 256;
        char[] list = new char[256];
        for(int i = 0; i<R; i++)
            list[i]=(char)i;

        while(!BinaryStdIn.isEmpty()){
            int n = BinaryStdIn.readInt(8);
            BinaryStdOut.write(list[n]);
            char ime = list[n];
            System.arraycopy(list,0,list,1,n);
            list[0] = ime;
        }
        BinaryStdOut.close();

    }

    public static void main(String[] args){
        if(args[0].equals("-")) {
            encode();
        }

        if(args[0].equals("+")){
            decode();
        }
    }
}

