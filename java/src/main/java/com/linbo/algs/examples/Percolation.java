package com.linbo.algs.examples;

import com.linbo.algs.datatypes.WeightedQuickUnionUF;

/**
 * Created by @linbojin on 9/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 */
public class Percolation {
  // 0 block, 1 opened,
  // 2 opened and connected with bottom,
  // 3 opened and connected with top,
  // 4 opened and connected with top and bottom
  private int[] sites;
  private int numberOfOpenSites;
  private WeightedQuickUnionUF uf;
  private int dim;
  private boolean isPercolates = false;

  // create n-by-n grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Input must be larger than 0!");
    }

    dim = n;
    int size = n * n;
    uf = new WeightedQuickUnionUF(size);
    sites = new int[size];
    for (int i = 0; i < size; i++) {
      sites[i] = 0;
    }
  }

  // validate that p is a valid index
  private void validate(int p) {
    if (p < 1 || p > dim) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (dim - 1));
    }
  }

  private boolean insideSites(int row, int col) {
    return row > 0 && row <= dim && col > 0 && col <= dim;
  }

  // union opened sites to it's neighbours
  private void chainNeighbours(int row, int col) {
    int index = (row - 1) * dim + col - 1;
    int[][] neighbours = {{row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}};
    int value = sites[index];

    for (int[] arr : neighbours) {
      int i = arr[0];
      int j = arr[1];
      if (insideSites(i, j) && isOpen(i, j)) {
        int k = (i - 1) * dim + j - 1;
        int status = sites[uf.find(k)];
        if ((value == 2 && status == 3) || (value == 3 && status == 2)) value = 4;
        else value = Math.max(value, status);
        uf.union(index, k);
      }
    }
    if (value == 4) isPercolates = true;
    sites[uf.find(index)] = value;
  }

  // open site (row, col) if it is not open already
  public void open(int row, int col) {
    if (!isOpen(row, col)) {
      int index = (row - 1) * dim + col - 1;
      if (dim == 1) {
        sites[index] = 4;
        isPercolates = true;
      } else {
        int value = 1;
        if (row == dim) value = 2;
        else if (row == 1) value = 3;
        sites[index] = value;
        chainNeighbours(row, col);
      }
      numberOfOpenSites++;
    }
  }

  // is site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row);
    validate(col);

    int index = (row - 1) * dim + col - 1;
    return sites[index] > 0;
  }

  // is site (row, col) full?
  public boolean isFull(int row, int col) {
    validate(row);
    validate(col);

    // handle bottom line sites
    int index = (row - 1) * dim + col - 1;
    return sites[uf.find(index)] >= 3;
  }

  // number of open sites
  public int numberOfOpenSites() {
    return numberOfOpenSites;
  }

  // does the system percolate?
  public boolean percolates() {
    return isPercolates;
  }


  // test client (optional)
  public static void main(String[] args) {
    Percolation pc = new Percolation(5);
    System.out.println(pc.percolates());           // false
    System.out.println(pc.numberOfOpenSites());
    System.out.println(pc.isOpen(1, 1));   // false
    System.out.println("********************");
    pc.open(1, 1);
    pc.open(1, 2);
    pc.open(1, 4);
    pc.open(2, 4);
    pc.open(3, 2);
    pc.open(3, 4);
    pc.open(3, 5);
    pc.open(4, 1);
    pc.open(4, 3);
    pc.open(5, 3);
    for (int i = 1; i < 6; i++) {
      for (int j = 1; j < 6; j++) {
        System.out.print(pc.sites[(i - 1) * 5 + j - 1]);
      }
      System.out.println();
    }
    System.out.println(pc.percolates());          // false
    System.out.println(pc.isFull(3, 2));  // false
    System.out.println(pc.isFull(3, 5));  // true

    System.out.println("********************");
    pc.open(3, 3);
    for (int i = 1; i < 6; i++) {
      for (int j = 1; j < 6; j++) {
        System.out.print(pc.sites[(i - 1) * 5 + j - 1]);
      }
      System.out.println();
    }
    System.out.println(pc.percolates());          // true
    System.out.println(pc.isFull(3, 2));  // true
    pc.open(3, 3);
    System.out.println(pc.numberOfOpenSites());    // 11

  }
}
