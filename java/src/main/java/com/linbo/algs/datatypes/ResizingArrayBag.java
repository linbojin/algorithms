package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 14/1/17.
 *  This class represents a bag of generic items.
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 */
public class ResizingArrayBag<Item> implements Iterable<Item> {
  private Item[] arr;
  private int size;

  public ResizingArrayBag() {
    arr = (Item []) new Object[2];
    size = 0;
  }

  public boolean isEmpty() { return size == 0; }

  public int size() { return size; }

  private void resize(int capacity) {
    assert capacity >= size;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      temp[i] = arr[i];
    }
    arr = temp;
    // alternative implementation
    // arr = java.util.Arrays.copyOf(arr, capacity);
  }

  public void add(Item item) {
    if (size == arr.length) { resize(2 * arr.length); }
    arr[size++] = item;
  }

  public Iterator<Item> iterator() { return new ArrayIterator(); }

  private class ArrayIterator implements Iterator<Item> {
    private int i = 0;

    public boolean hasNext() { return i < size; }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) { throw new java.util.NoSuchElementException(); }
      return arr[i++];
    }
  }


  public static void main(String args[]) {
    LinkedBag<String> bag = new LinkedBag<String>();
    String input = "to be or not to be that is";
    String[] arr = input.split(" ");
    for (String s : arr) {
      bag.add(s);
    }
    // is that be to not or be to
    for (String s : bag) {
      System.out.print(s);
      System.out.print(" ");
    }
    System.out.println();
    System.out.println(bag.isEmpty());
    System.out.println(bag.size());
  }
}
