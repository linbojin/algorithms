package com.linbo.algs.examples;

import com.linbo.algs.util.StdStats;

import java.util.Random;

/**
 * Created by @linbojin on 9/1/17.
 */
public class PercolationStats {

  private double[] fractions;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Aruments must be larger than 0!");
    }

    fractions = new double[trials];
    Random random = new Random();

    for (int i = 0; i < trials; i++) {
      Percolation pc = new Percolation(n);
      int count = 0;
      while (!pc.percolates()) {
        int row = random.nextInt(n) + 1;
        int col = random.nextInt(n) + 1;
        pc.open(row, col);
      }
      fractions[i] = pc.numberOfOpenSites() / (n * n * 1.0d);
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(fractions);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(fractions);
  }

  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(fractions.length);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(fractions.length);
  }

  // test client (described below)
  public static void main(String[] args) {
    int n = 200;
    int t = 100;
    PercolationStats ps = new PercolationStats(n, t);
    System.out.println("mean\t\t = " + ps.mean());
    System.out.println("stddev\t\t = " + ps.stddev());
    System.out.println("95% confidence interval \t = " + ps.confidenceLo() + ", " + ps.confidenceHi());

    System.out.println("********************");
    n = 2;
    t = 10000;
    PercolationStats ps1 = new PercolationStats(n, t);
    System.out.println("mean\t\t = " + ps1.mean());
    System.out.println("stddev\t\t = " + ps1.stddev());
    System.out.println("95% confidence interval \t = " + ps1.confidenceLo() + ", " + ps1.confidenceHi());

    System.out.println("********************");
    n = 2;
    t = 100000;
    PercolationStats ps2 = new PercolationStats(n, t);
    System.out.println("mean\t\t = " + ps2.mean());
    System.out.println("stddev\t\t = " + ps2.stddev());
    System.out.println("95% confidence interval \t = " + ps2.confidenceLo() + ", " + ps2.confidenceHi());
  }
}
