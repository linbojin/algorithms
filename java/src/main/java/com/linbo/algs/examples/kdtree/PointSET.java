package com.linbo.algs.examples.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

/**
 * Created by @linbojin on 5/2/17.
 *  ref: http://coursera.cs.princeton.edu/algs4/assignments/kdtree.html
 */
public class PointSET {

  private TreeSet<Point2D> rbTree;

  // construct an empty set of points
  public PointSET() {
    rbTree = new TreeSet<Point2D>();
  }

  public boolean isEmpty() {
    return rbTree.isEmpty();
  }

  public int size() {
    return rbTree.size();
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();
    if (!contains(p)) rbTree.add(p);
  }

  public boolean contains(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();
    return rbTree.contains(p);
  }

  public void draw() {
    for (Point2D p : rbTree) {
      StdDraw.point(p.x(), p.y());
    }
  }

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new java.lang.NullPointerException();
    Queue<Point2D> pq = new Queue<Point2D>();
    for (Point2D p : rbTree) {
      if (rect.contains(p)) pq.enqueue(p);
    }
    return pq;
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();
    if (rbTree.isEmpty()) return null;

    double min = Double.MAX_VALUE;
    Point2D nearestP = null;
    for (Point2D point: rbTree) {
      double d = point.distanceSquaredTo(p);
      if (d < min) {
        min = d;
        nearestP = point;
      }
    }
    return nearestP;
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {

  }

}
