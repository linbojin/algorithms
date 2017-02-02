package com.linbo.algs.examples.puzzle;

import edu.princeton.cs.algs4.*;

/**
 * Created by @linbojin on 25/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
 */
public class Solver {

  private SearchNode finalStep;
  private boolean isSolvable = false;

  private class SearchNode implements Comparable<SearchNode> {
    final Board board;
    final int moves;
    final SearchNode pre;
    final int priority;

    public SearchNode(Board board, int moves, SearchNode pre) {
      this.board = board;
      this.moves = moves;
      this.pre = pre;
      this.priority = board.manhattan() + moves;
    }

    public int compareTo(SearchNode that) {
      if (priority < that.priority) return -1;
      else if (priority > that.priority) return 1;
      else return 0;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) throw new java.lang.NullPointerException();

    MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    pq.insert(new SearchNode(initial, 0, null));
    SearchNode sn = pq.delMin();

    MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();
    twinPQ.insert(new SearchNode(initial.twin(), 0, null));
    SearchNode twinSN = twinPQ.delMin();

    while (!sn.board.isGoal() && !twinSN.board.isGoal()) {
      for (Board b: sn.board.neighbors()) {
        if (sn.pre == null || !b.equals(sn.pre.board)) pq.insert(new SearchNode(b, sn.moves+1, sn));
      }
      for (Board b: twinSN.board.neighbors()) {
        if (twinSN.pre == null || !b.equals(twinSN.pre.board)) twinPQ.insert(new SearchNode(b, twinSN.moves+1, twinSN));
      }

      sn = pq.delMin();
      twinSN = twinPQ.delMin();
    }

    if (sn.board.isGoal()) {
      isSolvable = true;
      finalStep = sn;
    }
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return isSolvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (isSolvable()) {
      return finalStep.moves;
    } else {
      return -1;
    }
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) {
      return null;
    } else {
      Stack<Board> stack = new Stack<Board>();
      SearchNode sn = finalStep;
      while (sn != null) {
        stack.push(sn.board);
        sn = sn.pre;
      }
      return stack;
    }
  }

  // solve a slider puzzle (given below)
  public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}
