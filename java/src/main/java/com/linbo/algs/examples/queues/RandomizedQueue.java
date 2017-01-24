package com.linbo.algs.examples.queues;

import java.util.Iterator;

import com.linbo.algs.util.StdRandom;

/**
 * Created by @linbojin on 13/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 * RandomizedQueue: a double-ended queue or deque (pronounced "deck") is a
 * generalization of a stack and a queue that supports adding and removing
 * items from either the front or the back of the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

  // Memory: 16 + 8 + 4 + 4 + 4 + 4 + 24 + N * 4 * 2
  //       = 64 + 8N
  private Item[] q;       // queue elements
  private int size;          // number of elements on queue
  private int first;      // index of first element of queue
  private int last;       // index of next available slot

  // construct an empty randomized queue
  public RandomizedQueue() {
    q = (Item[]) new Object[2];
    size = 0;
    first = 0;
    last = 0;
  }

  // is the queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the queue
  public int size() {
    return size;
  }

  // resize the underlying array
  private void resize(int capacity) {
    assert capacity >= size;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      temp[i] = q[(first + i) % q.length];
    }
    q = temp;
    first = 0;
    last = size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException("Input item can not be null!");
    }

    // double size of array if necessary and recopy to front of array
    if (size == q.length) resize(2 * q.length);   // double size of array if necessary
    q[last++] = item;                        // add item
    if (last == q.length) last = 0;          // wrap-around
    size++;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("Queue is empty!");
    }

    int index = StdRandom.uniform(size) + first;
    int i = index % q.length;
    Item item = q[i];

    last = (last + q.length - 1) % q.length;

    if (i != last) {
      q[i] = q[last];
    }

    q[last] = null;
    size--;

    // shrink size of array if necessary
    if (size > 0 && size == q.length / 4) resize(q.length / 2);

    return item;
  }

  // return (but do not remove) a random item
  public Item sample() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("Queue is empty!");
    }

    int index = StdRandom.uniform(size) + first;
    return q[index % q.length];
  }

  // an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new ArrayIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ArrayIterator implements Iterator<Item> {
    private int i = 0;
    private int[] inxArr;

    public ArrayIterator() {
      inxArr = StdRandom.permutation(size);
    }

    public boolean hasNext() {
      return i < size;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      Item item = q[(inxArr[i] + first) % q.length];
      i++;
      return item;
    }
  }


  // unit testing (optional)
  public static void main(String[] args) {
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    System.out.println(rq.isEmpty());
    rq.enqueue(1);
    rq.enqueue(2);
    rq.enqueue(3);
    rq.enqueue(4);
    rq.enqueue(5);

    for (int i : rq) {
      System.out.print(i);
      System.out.print(" ");
    }
    System.out.println("\n*******");

    System.out.println(rq.sample());
    System.out.println(rq.size());   // 5
    System.out.println("*******");

    System.out.println(rq.isEmpty());
    System.out.println(rq.dequeue());
    System.out.println(rq.dequeue());
    System.out.println(rq.sample());
    System.out.println(rq.size());   // 3

    System.out.println("*******");
    System.out.println(rq.dequeue());
    System.out.println(rq.dequeue());
    System.out.println(rq.size());   // 1

    System.out.println("*******");
    System.out.println(rq.sample());
    System.out.println(rq.dequeue());
    System.out.println(rq.size());   // 0

    System.out.println("*******");
    RandomizedQueue<Integer> rqOne = new RandomizedQueue<Integer>();
    System.out.println(rq.size());
    rq.enqueue(4);
    System.out.println(rq.size());
    rq.enqueue(5);
    System.out.println(rq.dequeue());

  }

}

