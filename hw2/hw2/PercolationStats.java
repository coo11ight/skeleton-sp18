package hw2;

import edu.princeton.cs.algs4.StdStats ;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {


    double testMean, testStddev, testConfidenceLow, testConfidenceHigh;


    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) throw new RuntimeException("illegal input ");
        int[] results = new int[T];
        Percolation p1;
        int x,y;
        for (int t=0; t < T; t++){
                p1 = pf.make(N);
                for (int i=0; i < N*N; i++){
                     x = StdRandom.uniform(0,N);
                     y = StdRandom.uniform(0,N);
                    while (p1.isOpen(x,y)){
                        x = StdRandom.uniform(0,N);
                        y = StdRandom.uniform(0,N);
                    }
                    p1.open(x,y);
                    if (p1.percolates()) {
                        results[t] = i + 1;
                        break;
                    }
            }
        }
        testMean = StdStats.mean(results)  / N / N;
        testStddev = StdStats.stddev(results) ;
        testConfidenceLow = testMean - 1.96 * testStddev / Math.sqrt(T);
        testConfidenceHigh = testMean + 1.96 * testStddev / Math.sqrt(T);


    }   // perform T independent experiments on an N-by-N grid
    public double mean(){
        return testMean;
    }                                           // sample mean of percolation threshold
    public double stddev(){
        return testStddev;
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow()
    {
        return testConfidenceLow;
    }// low endpoint of 95% confidence interval
    public double confidenceHigh(){
        return testConfidenceHigh;
    }                                 // high endpoint of 95% confidence interval

    public static void main(String args[]){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p1 = new PercolationStats(1000,100,pf);
        System.out.println(p1.mean());
        System.out.println(p1.stddev());
    }

}
