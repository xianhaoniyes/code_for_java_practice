import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


public class PercolationStats {

    private  double mean;
    private  double stddev;
    private  double lowpoint;
    private  double highpoint;
    private double[] sample;
    private int trails;

    public PercolationStats(int n, int trials) {
        Percolation percolation;
        if (n <=0 || trials <= 0)
            throw new IllegalArgumentException("Illegal argument");

        sample = new double[trials];
        this.trails = trials;
        int row,col;
        double amount = n*n;

        for(int i = 0;i < trials;i++){
            percolation = new Percolation(n);

            while(!percolation.percolates()){
                row = StdRandom.uniform(n);
                col = StdRandom.uniform(n);
                percolation.open(row+1,col+1);
             }

        sample[i] = percolation.numberOfOpenSites()/amount;

        }


    }

    public double mean(){
        return StdStats.mean(sample);
    }

    public double stddev(){ return StdStats.stddev(sample); }
    public double confidenceLo(){
        return mean - (1.96 * stddev / Math.sqrt((double)trails));
    }
    public double confidenceHi(){
        return mean + (1.96 * stddev / Math.sqrt((double)trails));
    }

    public static void main(String[] args){
        PercolationStats percolationStats;
        int n,trials;
//        n = Integer.parseInt(args[0]);
//
//        trials = Integer.parseInt(args[1]);
        n=2;
        trials =10000;
        percolationStats = new PercolationStats(n,trials);

        percolationStats.mean = percolationStats.mean();
        percolationStats.stddev = percolationStats.stddev();
        percolationStats.lowpoint = percolationStats.confidenceLo();
        percolationStats.highpoint = percolationStats.confidenceHi();
        StdOut.printf("mean                    = %.16f\n",percolationStats.mean);
        StdOut.printf("stddev                  = %.16f\n",percolationStats.stddev);
        StdOut.printf("mean                    = [%.16f, %.16f]",percolationStats.lowpoint,percolationStats.highpoint);

    }
}
