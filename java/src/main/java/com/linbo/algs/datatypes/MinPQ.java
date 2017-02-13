package com.linbo.algs.datatypes;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by @linbojin on 24/1/17.
 *  This class represents a priority queue of generic keys.
 *  It supports the usual insert and delete-the-maximum
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  This implementation uses a binary heap.
 *  The insert and delete-the-maximum operations take
 *  logarithmic amortized time.
 */
public class MinPQ<Key> implements Iterable<Key> {
  private Key[] pq;                    // store items at indices 1 to n
  private int n;                       // number of items on priority queue
  private Comparator<Key> comparator;  // optional Comparator

  public MinPQ(int initCapacity) {
    pq = (Key[]) new Object[initCapacity + 1];
    n = 0;
  }

  public MinPQ() {
    this(1);
  }

  public MinPQ(int initCapacity, Comparator<Key> comparator) {
    this.comparator = comparator;
    pq = (Key[]) new Object[initCapacity + 1];
    n = 0;
  }

  public MinPQ(Comparator<Key> comparator) {
    this(1, comparator);
  }

  public MinPQ(Key[] keys) {
    n = keys.length;
    pq = (Key[]) new Object[keys.length + 1];
    for (int i = 0; i < n; i++)
      pq[i+1] = keys[i];
    for (int k = n/2; k >= 1; k--)
      sink(k);
  }

  public boolean isEmpty() {
    return n == 0;
  }

  public int size() {
    return n;
  }

  public Key min() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Priority queue underflow");
    return pq[1];
  }

  // helper function to double the size of the heap array
  private void resize(int capacity) {
    assert capacity > n;
    Key[] temp = (Key[]) new Object[capacity];
    for (int i = 1; i <= n; i++) {
      temp[i] = pq[i];
    }
    pq = temp;
  }

  public void insert(Key x) {
    // double size of array if necessary
    if (n >= pq.length - 1) resize(2 * pq.length);

    // add x, and percolate it up to maintain heap invariant
    pq[++n] = x;
    swim(n);
  }

  public Key delMin() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Priority queue underflow");
    Key min = pq[1];
    exch(1, n--);
    sink(1);
    pq[n+1]=null;
    if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
    return min;
  }

  /***************************************************************************
   * Helper functions to restore the heap invariant.
   ***************************************************************************/

  private void swim(int k) {
    while (k > 1 && greater(k/2, k)) {
      exch(k, k/2);
      k = k/2;
    }
  }

  private void sink(int k) {
    while (2*k <= n) {
      int j = 2*k;
      if (j<n && greater(j, j+1)) j++;
      if (!greater(k, j)) break;
      exch(k, j);
      k = j;
    }
  }

  /***************************************************************************
   * Helper functions for compares and swaps.
   ***************************************************************************/
  private boolean greater(int i, int j) {
    if (comparator == null) {
      return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
    }
    else {
      return comparator.compare(pq[i], pq[j]) > 0;
    }
  }

  private void exch(int i, int j) {
    Key swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
  }

  /***************************************************************************
   * Iterator.
   ***************************************************************************/
  public Iterator<Key> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Key> {

    // create a new pq
    private MinPQ<Key> copy;

    // add all items to copy of heap
    // takes linear time since already in heap order so no keys move
    public HeapIterator() {
      if (comparator == null) copy = new MinPQ<Key>(size());
      else                    copy = new MinPQ<Key>(size(), comparator);
      for (int i = 1; i <= n; i++)
        copy.insert(pq[i]);
    }

    public boolean hasNext()  { return !copy.isEmpty();                     }
    public void remove()      { throw new UnsupportedOperationException();  }

    public Key next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      return copy.delMin();
    }
  }

  public static void main(String args[]) {
    MinPQ<Integer> pq = new MinPQ<Integer>();
    pq.insert(1);
    pq.insert(4);
    pq.insert(5);
    pq.insert(3);
    pq.insert(2);

    System.out.println(pq.delMin());   // 1
    System.out.println(pq.delMin());   // 2
    System.out.println(pq.delMin());   // 3
    System.out.println(pq.delMin());   // 4
    pq.insert(22);
    System.out.println(pq.size());      // 2
    System.out.println(pq.isEmpty());

    System.out.println(pq.delMin());   // 5
    System.out.println(pq.delMin());   // 22
  }

}
