package com.linbo.algs.examples.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by @linbojin on 5/2/17.
 *  ref: http://coursera.cs.princeton.edu/algs4/assignments/kdtree.html
 */
public class KdTree {
  private static final boolean EVEN = true;
  private static final boolean ODD = false;
  private double min;
  private Point2D nearestP;

  private Node root;

  private class Node {
    private Point2D point;
    private Node left, right;
    private boolean type;  // color of parent link
    private RectHV rect;
    private int size;

    public Node(Point2D point, boolean type, int size, RectHV rect) {
      this.point = point;
      this.type = type;
      this.size = size;
      this.rect = rect;
    }
  }

  // construct an empty set of points
  public KdTree() { }

  public int size() { return size(root); }

  public boolean isEmpty() { return root == null; }

  private int size(Node x) {
    if (x == null) return 0;
    return x.size;
  }

  public boolean contains(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();

    Node x = root;
    while (x != null) {
      if (p.equals(x.point)) return true;
      int cmp = less(p, x.point, x.type);
      if (cmp < 0) x = x.left;
      else x = x.right;
    }
    return false;
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();
    if (root == null) root = new Node(p, EVEN, 1,
            new RectHV(0, 0, 1, 1));
    else root = insert(root, p, EVEN);
  }

  private Node insert(Node h, Point2D p, boolean type) {
    int cmp = less(p, h.point, h.type);
    if (p.equals(h.point)) return h;
    if (cmp < 0) {
      if (h.left == null) h.left = new Node(p, !type, 1, getRect(h, true));
      else h.left = insert(h.left, p, !type);
    }
    else {
      if (h.right == null) h.right = new Node(p, !type, 1, getRect(h, false));
      else h.right = insert(h.right, p, !type);
    }
    h.size = size(h.left) + size(h.right) + 1;

    return h;
  }

  private RectHV getRect(Node h, boolean small) {
    if (h.type == EVEN) {
      if (small) return new RectHV(h.rect.xmin(), h.rect.ymin(), h.point.x(), h.rect.ymax());
      else return new RectHV(h.point.x(), h.rect.ymin(), h.rect.xmax(), h.rect.ymax());
    }
    else {
      if (small) return new RectHV(h.rect.xmin(), h.rect.ymin(), h.rect.xmax(), h.point.y());
      else return new RectHV(h.rect.xmin(), h.point.y(), h.rect.xmax(), h.rect.ymax());
    }
  }

  private int less(Point2D p, Point2D q, boolean type) {
    Comparator<Point2D> comparator;
    if (type == EVEN) comparator = Point2D.X_ORDER;
    else comparator = Point2D.Y_ORDER;

    if (comparator.compare(p, q) < 0) return -1;
    else if (comparator.compare(p, q) > 0) return 1;
    else return 0;
  }

  public void draw() {
    draw(root);
  }

  private void draw(Node x) {
    if (x != null) {
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(0.01);
      StdDraw.point(x.point.x(), x.point.y());
      StdDraw.setPenRadius();
      if (x.type == EVEN){
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(x.point.x(), x.rect.ymin(), x.point.x(), x.rect.ymax());
      }
      else {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.point.y());
      }
      draw(x.left);
      draw(x.right);
    }
  }

  // all points that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new java.lang.NullPointerException();
    Queue<Point2D> queue = new Queue<Point2D>();

    range(root, queue, rect);
    return queue;
  }

  // add the keys between lo and hi in the subtree rooted at x
  // to the queue
  private void range(Node x, Queue<Point2D> queue, RectHV rect) {
    if (x == null) return;

    if (rect.contains(x.point)) {
      queue.enqueue(x.point);
      range(x.left, queue, rect);
      range(x.right, queue, rect);
    } else {
      if (x.type == EVEN) {
        if (rect.xmax() < x.point.x()) range(x.left, queue, rect);
        else if (rect.xmin() > x.point.x()) range(x.right, queue, rect);
        else {
          range(x.left, queue, rect);
          range(x.right, queue, rect);
        }
      } else {
        if (rect.ymax() < x.point.y()) range(x.left, queue, rect);
        else if (rect.ymin() > x.point.y()) range(x.right, queue, rect);
        else {
          range(x.left, queue, rect);
          range(x.right, queue, rect);
        }
      }
    }
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new java.lang.NullPointerException();

    min = Double.MAX_VALUE;
    nearestP = null;
    nearest(root, p);
    return nearestP;
  }

  private void nearest(Node x, Point2D p) {
    if (x == null) return;

    if (min >= x.rect.distanceSquaredTo(p)) {
      double newDist = x.point.distanceSquaredTo(p);
      if (newDist < min) {
        min = newDist;
        nearestP = x.point;
      }

      if (isSmallSide(x, p)) {
        nearest(x.left, p);
        nearest(x.right, p);
      }
      else {
        nearest(x.right, p);
        nearest(x.left, p);
      }
    }
  }

  private boolean isSmallSide(Node x, Point2D p) {
    if (x.type == EVEN) return p.x() < x.point.x();
    else return p.y() < x.point.y();
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {

  }

}

