package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 14/1/17.
 */
public class LinkedQueue<Item> implements Iterable<Item> {
  private int size;
  private Node first;
  private Node last;

  private class Node {
    private Item item;
    private Node next;
  }

  public LinkedQueue() {
    first = null;
    last = null;
    size = 0;
  }

  public boolean isEmpty() { return first == null; }

  public int size() { return size; }

  public void enqueue(Item item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else oldLast.next = last;
    size++;
  }

  public Item dequeue() {
    if (isEmpty()) { throw new java.util.NoSuchElementException("Queue is empty!"); }
    Item item = first.item;
    first = first.next;
    size--;
    if (isEmpty()) last = null;
    return item;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() { return current != null; }
    public void remove()      { throw new UnsupportedOperationException();  }
    public Item next() {
      if (!hasNext()) { throw new java.util.NoSuchElementException(); }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }


  public static void main(String args[]) {
    LinkedQueue<Integer> lq = new LinkedQueue<Integer>();
    lq.enqueue(1);
    lq.enqueue(4);
    lq.enqueue(5);
    lq.enqueue(3);
    lq.enqueue(2);

    System.out.println(lq.dequeue());   // 1
    System.out.println(lq.dequeue());   // 4
    System.out.println(lq.dequeue());   // 5
    System.out.println(lq.dequeue());   // 3
    lq.enqueue(22);
    System.out.println(lq.size());      // 2
    System.out.println(lq.isEmpty());

    System.out.println(lq.dequeue());   // 2
    System.out.println(lq.dequeue());   // 22
  }

}
