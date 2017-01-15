package com.linbo.algs.examples;

import java.util.Iterator;

/**
 * Created by @linbojin on 13/1/17.
 * Ref: http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 * Deque: a double-ended queue or deque (pronounced "deck") is a generalization of
 * a stack and a queue that supports adding and removing items from
 * either the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {

  // Memory: 16 + 8 + 8 + 4 + 4 + N * (16 + 8 + 8 + 8 + 4 + 4)
  //         =  48N + 40
  private Node<Item> first;       // beginning of queue
  private Node<Item> last;        // end of queue
  private int size;


  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
    size = 0;
  }

  private class Node<Item> {        // 16
    private Item item;              // 4
    private Node<Item> prev;
    private Node<Item> next;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException("Item can not be null!");
    }

    Node<Item> oldFirst = first;
    first = new Node<Item>();
    first.item = item;
    first.next = oldFirst;
    first.prev = null;
    if (oldFirst == null) {
      last = first;
    } else {
      oldFirst.prev = first;
    }
    size++;
  }

  // add the item to the end
  public void addLast(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException("Item can not be null!");
    }

    Node<Item> oldLast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    last.prev = oldLast;
    if (oldLast == null) {
      first = last;
    } else {
      oldLast.next = last;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("Deque is empty!");
    }

    Item item = first.item;
    first = first.next;
    if (first == null) {
      last = null;
    } else {
      first.prev = null;
    }
    size--;
    return item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("Deque is empty!");
    }

    Item item = last.item;
    last = last.prev;
    if (last == null) {
      first = null;
    } else {
      last.next = null;
    }
    size--;
    return item;
  }

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new ListIterator<Item>(first);
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> first) {
      current = first;
    }

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new java.util.NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }


  // unit testing (optional)
  public static void main(String[] args) {
    Deque<Integer> dq = new Deque<Integer>();
    System.out.println(dq.isEmpty());
    dq.addFirst(1);
    dq.addFirst(2);
    dq.addFirst(3);
    dq.addFirst(4);
    dq.addFirst(5);
    dq.addLast(1);
    dq.addLast(2);
    dq.addLast(3);
    dq.addLast(4);
    dq.addLast(5);
    // 5 4 3 2 1 1 2 3 4 5
    for (int i : dq) {
      System.out.print(i);
      System.out.print(" ");
    }
    System.out.println();

    System.out.println(dq.removeFirst());   // 5
    System.out.println(dq.removeLast());    // 5
    System.out.println(dq.size());
    System.out.println(dq.isEmpty());

  }

}
