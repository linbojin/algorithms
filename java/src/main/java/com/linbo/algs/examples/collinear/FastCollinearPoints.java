package com.linbo.algs.examples.collinear;

import com.linbo.algs.util.In;
import com.linbo.algs.util.StdDraw;
import com.linbo.algs.util.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by @linbojin on 23/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/collinear.html
 */
public class FastCollinearPoints {
  private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
  private HashMap<Double, List<Point>> foundSegments = new HashMap<Double, List<Point>>();

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] pts) {
    if (pts == null) throw new java.lang.NullPointerException();
    int n = pts.length;
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      if (pts[i] == null) throw new java.lang.NullPointerException();
      points[i] = pts[i];
    }

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
      }
    }

    for (int i = 0; i < n; i++) {
      Point p = pts[i];
      Arrays.sort(points, p.slopeOrder());

      int j = 0;
      double slope = 0;
      double previousS = Double.NEGATIVE_INFINITY;
      List<Point> slopePoints = new ArrayList<Point>();

      while (j < n - 1) {
        int count = 1;
        while (true) {
          slopePoints.add(points[j]);
          if (j == n - 1) {
            break;
          }
          slope = p.slopeTo(points[++j]);
          if (slope != previousS) {
            break;
          }
          count++;
        }

        if (count > 2) {
          slopePoints.add(p);
          addSegmentIfNew(slopePoints, previousS);
        }

        previousS = slope;
        slopePoints.clear();
      }

    }
  }

  private void addSegmentIfNew(List<Point> slopePoints, double slope) {
    List<Point> endPoints = foundSegments.get(slope);

    Point startPoint = slopePoints.get(0);
    Point endPoint = slopePoints.get(0);
    for (int i = 1; i < slopePoints.size(); i++) {
      Point p = slopePoints.get(i);
      if (p.compareTo(startPoint) < 0) startPoint = p;
      else if (p.compareTo(endPoint) > 0) endPoint = p;
    }

    if (endPoints == null) {
      endPoints = new ArrayList<Point>();
      endPoints.add(endPoint);
      foundSegments.put(slope, endPoints);
      segments.add(new LineSegment(startPoint, endPoint));
    } else {
      for (Point currentEndPoint : endPoints) {
        if (currentEndPoint.compareTo(endPoint) == 0) {
          return;
        }
      }
      endPoints.add(endPoint);
      segments.add(new LineSegment(startPoint, endPoint));
    }
  }


  // the number of line segments
  public int numberOfSegments() {
    return segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    return segments.toArray(new LineSegment[segments.size()]);
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

}
