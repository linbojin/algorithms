package com.linbo.algs.examples.convexhull;

import com.linbo.algs.datatypes.LinkedStack;

import java.util.Arrays;

/**
 * Created by @linbojin on 21/1/17.
 *  This class provides methods for computing the convex hull
 *  of a set of N points in the plane.
 *
 *  The implementation uses the Graham-Scan convex hull algorithm.
 *  It runs in O(NlogN) time in the worst case and uses O(N) extra memory.
 */
public class GrahamScan {
  private LinkedStack<Point2D> hull = new LinkedStack<Point2D>();

  public GrahamScan(Point2D[] pts) {

    // defensive copy
    int n = pts.length;
    Point2D[] points = new Point2D[n];
    for (int i = 0; i < n; i++)
      points[i] = pts[i];

    // preprocess so that points[0] has lowest y-coordinate; break ties by x-coordinate
    // points[0] is an extreme point of the convex hull
    // (alternatively, could do easily in linear time)
    Arrays.sort(points);

    // sort by polar angle with respect to base point points[0],
    // breaking ties by distance to points[0]
    Arrays.sort(points, 1, n, points[0].polarOrder());

    hull.push(points[0]);       // p[0] is first extreme point

    // find index k1 of first point not equal to points[0]
    int k1;
    for (k1 = 1; k1 < n; k1++)
      if (!points[0].equals(points[k1])) break;
    if (k1 == n) return;        // all points equal

    // find index k2 of first point not collinear with points[0] and points[k1]
    int k2;
    for (k2 = k1+1; k2 < n; k2++)
      if (Point2D.ccw(points[0], points[k1], points[k2]) != 0) break;
    hull.push(points[k2-1]);    // points[k2-1] is second extreme point

    // Graham scan; note that points[n-1] is extreme point different from points[0]
    for (int i = k2; i < n; i++) {
      Point2D top = hull.pop();
      while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
        top = hull.pop();
      }
      hull.push(top);
      hull.push(points[i]);
    }

  }

  public Iterable<Point2D> hull() {
    LinkedStack<Point2D> s = new LinkedStack<Point2D>();
    for (Point2D p : hull) s.push(p);
    return s;
  }

  public static void main(String[] args) {
    Point2D[] points = new Point2D[8];
    points[0] = new Point2D(-2, 3);
    points[1] = new Point2D(0, 1);
    points[2] = new Point2D(0, 0);
    points[3] = new Point2D(-3, 1);
    points[4] = new Point2D(2, 2);
    points[5] = new Point2D(-4, 5);
    points[6] = new Point2D(2, 4);
    points[7] = new Point2D(1, 3);

    GrahamScan graham = new GrahamScan(points);
    for (Point2D p : graham.hull())
      System.out.println(p);
  }
}
