package com.linbo.algs.examples.collinear;

import com.linbo.algs.util.In;
import com.linbo.algs.util.StdDraw;
import com.linbo.algs.util.StdOut;
import com.linbo.algs.datatypes.LinkedStack;

/**
 * Created by @linbojin on 22/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 */
public class BruteCollinearPoints {
  private LineSegment[] ls;
  private int count = 0;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] pts) {
    if (pts == null) throw new java.lang.NullPointerException();
    int n = pts.length;
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      if (pts[i] == null) throw new java.lang.NullPointerException();
      points[i] = pts[i];
    }

    for (int i = 0; i < n; i++) {
      if (points[i] == null) throw new java.lang.NullPointerException();
      for (int j = i + 1; j < n; j++) {
        if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
      }
    }


    LinkedStack<LineSegment> stack = new LinkedStack<LineSegment>();

    for (int i = 0; i < n - 3; i++) {
      for (int j = i + 1; j < n - 2; j++) {
        for (int k = j + 1; k < n - 1; k++) {
          for (int l = k + 1; l < n; l++) {
            Point p = points[i];
            Point q = points[j];
            Point r = points[k];
            Point s = points[l];

            double slope1 = p.slopeTo(q);
            double slope2 = p.slopeTo(r);
            double slope3 = p.slopeTo(s);
            if (slope1 == slope2 && slope1 == slope3) {
              Point[] set = {p, q, r, s};
              Point min = q;
              Point max = q;
              for (int m = 0; m < 4; m++) {
                if (set[m].compareTo(min) < 0) min = set[m];
                if (set[m].compareTo(max) > 0) max = set[m];
              }

              count++;
              stack.push(new LineSegment(min, max));
            }
          }
        }
      }
    }

    int sn = stack.size();
    ls = new LineSegment[sn];
    for (int i = 0; i < sn; i++) {
      ls[i] = stack.pop();
    }

  }


  // the number of line segments
  public int numberOfSegments() {
    return count;
  }

  // the line segments
  public LineSegment[] segments() {
    return ls.clone();
  }

  public static void main(String[] args) {

    // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

}
