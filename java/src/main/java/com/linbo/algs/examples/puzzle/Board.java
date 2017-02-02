package com.linbo.algs.examples.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

/**
 * Created by @linbojin on 25/1/17.
 *  ref: http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 */
public class Board {
  private int[][] blocks;
  private int dim;
  private int blankX;
  private int blankY;

  // construct a board from an n-by-n array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    dim = blocks.length;
    this.blocks = new int[dim][dim];
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        this.blocks[i][j] = blocks[i][j];
        if (blocks[i][j] == 0) {
          blankX = i;
          blankY = j;
        }
      }
    }
  }

  // board dimension n
  public int dimension() {
    return dim;
  }

  // number of blocks out of place
  public int hamming() {
    int count = 0;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if (blocks[i][j] != i * dim + j + 1) count++;
      }
    }
    return count - 1;
  }

  // sum of Manhattan distances between blocks and goal
  public int manhattan() {
    int value = 0;
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        int num = blocks[i][j];
        if (num == 0) continue;
        int x = num / dim;
        int y = num % dim;
        if (y == 0) {
          x = x - 1;
          y = dim - 1;
        }
        else {
          y = y - 1;
        }

        value += Math.abs(i - x) + Math.abs(j - y);
      }
    }
    return value;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return hamming() == 0;
  }

  // a board that is obtained by exchanging any pair of blocks
  public Board twin() {
    int[][] copy = new int[dim][dim];
    for (int m = 0; m < dim; m++) {
      copy[m] = blocks[m].clone();
    }
    int tmp = copy[0][0];
    int tmp1 = copy[1][0];
    if (tmp == 0) {
      copy[1][0] = copy[0][1];
      copy[0][1] = tmp1;
    }
    else if (tmp1 == 0) {
      copy[0][0] = copy[0][1];
      copy[0][1] = tmp;
    }
    else {
      copy[0][0] = tmp1;
      copy[1][0] = tmp;
    }

    return new Board(copy);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null) return false;
    if (y.getClass() != this.getClass()) return false;
    Board that = (Board) y;
    return (this.dim == that.dim) && (Arrays.deepEquals(blocks, that.blocks));
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    return neighborsIterable();
  }

  private Iterable<Board> neighborsIterable() {
    Queue<Board> queue = new Queue<Board>();

    Queue<Integer> xs = new Queue<Integer>();
    Queue<Integer> ys = new Queue<Integer>();
    int x1 = blankX - 1;
    int x2 = blankX + 1;
    int y1 = blankY - 1;
    int y2 = blankY + 1;
    if (x1 >= 0 && x1 < dim) {
      xs.enqueue(x1);
      ys.enqueue(blankY);
    }
    if (x2 >= 0 && x2 < dim) {
      xs.enqueue(x2);
      ys.enqueue(blankY);
    }
    if (y1 >= 0 && y1 < dim) {
      xs.enqueue(blankX);
      ys.enqueue(y1);
    }
    if (y2 >= 0 && y2 < dim) {
      xs.enqueue(blankX);
      ys.enqueue(y2);
    }

    int count = xs.size();

    for (int k = 0; k < count; k++) {
      int i = xs.dequeue();
      int j = ys.dequeue();
      int[][] copy = new int[dim][dim];
      for (int m = 0; m < dim; m++) {
        copy[m] = blocks[m].clone();
      }
      swapWithBlank(copy, i, j);
      queue.enqueue(new Board(copy));
    }

    return queue;
  }

  private void swapWithBlank(int[][] blocks, int i, int j) {
    blocks[blankX][blankY] = blocks[i][j];
    blocks[i][j] = 0;
  }

  // string representation of this board (in the output format specified below)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(dim + "\n");
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        s.append(String.format("%2d ", blocks[i][j]));
      }
      s.append("\n");
    }
    return s.toString();
  }

  // unit tests (not graded)
  public static void main(String[] args) {
    int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    Board board = new Board(blocks);

    System.out.println(board);
    System.out.println(board.dim);
    System.out.println("hamming: " + board.hamming());
    System.out.println("manhattan:" + board.manhattan());
    System.out.println(board.isGoal());
    System.out.println(board.twin());

    System.out.println("**************Neighbors**************");
    for (Board b: board.neighbors()) {
      System.out.println(b);
    }

    System.out.println("**************Original**************");
    System.out.println(board);
  }
}

