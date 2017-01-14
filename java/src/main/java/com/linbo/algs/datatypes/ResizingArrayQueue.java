package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 14/1/17.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
  private Item[] arr;
  private int first;
  private int last;
  private int size;

  public ResizingArrayQueue() {
    arr = (Item []) new Object[2];
    first = 0;
    last = 0;
    size = 0;
  }

  public boolean isEmpty() { return size == 0; }

  public int size() { return size; }

  private void resize(int capacity) {
    assert capacity >= size;

    Item[] tmp = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      tmp[i] = arr[(first + i) % arr.length];
    }

    first = 0;
    last = size;
    arr = tmp;
  }

  public void enqueue(Item item) {
    if (size == arr.length) { resize(arr.length * 2); }
    arr[last++] = item;
    if (last == arr.length) last = 0;
    size++;
  }

  public Item dequeue() {
    if (isEmpty()) { throw new java.util.NoSuchElementException(); }

    Item item = arr[first];
    arr[first] = null;
    first++;
    if (first == arr.length) first = 0;
    size--;
    // shrink size of array if necessary
    if (size > 0 && size == arr.length/4) { resize(arr.length / 2); }
    return item;
  }

  public Iterator<Item> iterator() {
    return new ArrayIterator();
  }

  private class ArrayIterator implements Iterator<Item> {
    private int i = 0;
    public boolean hasNext() { return i < size; }
    public void remove() { throw new UnsupportedOperationException();  }

    public Item next() {
      if (!hasNext()) { throw new java.util.NoSuchElementException(); }
      Item item = arr[(first + i) % arr.length];
      i++;
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
