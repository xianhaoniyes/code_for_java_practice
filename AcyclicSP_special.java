import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class AcyclicSP_special {

    private int[] edgeTo;
    private double[] distTo;
    private double[] weights;
    private int width;
    private int height;
    private int amount;

    public AcyclicSP_special(double[][] energy){

        height = energy.length;
        width = energy[0].length;
        amount = width*height;
        edgeTo = new int[amount+2];
        distTo = new double[amount+2];
        weights = new double[amount+2];
        weights[0] = 0;
        // weights[N*N+1] = 0; may be don't need this line
        for (int i =0;i < height; i++)
            for(int j =0; j < width; j++){
                weights[i*width+j+1] = energy[i][j];
                //StdOut.printf("%d\n",i*width+j+1);
            }

        for(int v =1;v<amount+2;v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0.0;

       // StdOut.printf("the problem is relax\n");
        for(int v=0; v < amount+1;v++){
            relax(v);
        }
    }

    private void relax(int v){

        if(v == 0)
            for(int i = 1; i< width+1;i++){
            edgeTo[i] = 0;
            distTo[i] = 0;
            }

        else if(v >amount-width ){

            if(distTo[amount+1] > distTo[v]+weights[v])
            {distTo[amount+1] = distTo[v]+weights[v]; edgeTo[amount+1] = v;}

        }

       else if(v%width == 1) {
            if(distTo[v+width] > distTo[v]+weights[v])
            {distTo[v+width] = distTo[v]+weights[v];edgeTo[v+width] = v; }
            if(distTo[v+width+1] > distTo[v]+weights[v])
            {distTo[v+width+1] = distTo[v]+weights[v];edgeTo[v+width+1] = v;}
        }

        else if(v%width == 0){
            if(distTo[v+width] > distTo[v]+weights[v])
            {distTo[v+width] = distTo[v]+weights[v];edgeTo[v+width] = v; }
            if(distTo[v+width-1] > distTo[v]+weights[v])
            {distTo[v+width-1] = distTo[v]+weights[v];edgeTo[v+width-1] = v;}
        }

        else{
            if(distTo[v+width] > distTo[v]+weights[v])
            {distTo[v+width] = distTo[v]+weights[v];edgeTo[v+width] = v; }
            if(distTo[v+width-1] > distTo[v]+weights[v])
            {distTo[v+width-1] = distTo[v]+weights[v];edgeTo[v+width-1] = v;}
            if(distTo[v+width+1] > distTo[v]+weights[v])
            {distTo[v+width+1] = distTo[v]+weights[v];edgeTo[v+width+1] = v;}

        }
    }

    public int[] path(){
        int c;
        Stack<Integer> stack = new Stack<>();
        for(int i = edgeTo[amount+1]; i!= 0;i = edgeTo[i]){
            c = i%width;
            if(c==0)
                stack.push(width-1);
            else
                stack.push(c-1);

        }
        int[] path = new int[stack.size()];

        for(int i = 0; i<path.length; i++){
            path[i] =stack.pop();
        }
       // StdOut.printf("%d %d %d %d\n",edgeTo[31],edgeTo[26],edgeTo[edgeTo[26]],edgeTo[edgeTo[edgeTo[26]]],edgeTo[edgeTo[edgeTo[edgeTo[26]]]] );
       // StdOut.printf("%.2f\n",distTo[26]);
        return path;
    }




}
