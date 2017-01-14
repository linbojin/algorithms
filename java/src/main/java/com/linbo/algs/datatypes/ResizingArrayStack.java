package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 14/1/17.
 *  This class represents a last-in-first-out (LIFO) stack
 *  of generic items.
 *  This implementation uses a resizing array, which double the underlying array
 *  when it is full and halves the underlying array when it is one-quarter full.
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
  private Item[] arr;
  private int size;

  public ResizingArrayStack() {
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

  public void push(Item item) {
    if (size == arr.length) { resize(2 * arr.length); }
    arr[size++] = item;
  }

  public Item pop() {
    if (isEmpty()) { throw new java.lang.NullPointerException("Stack is empty!"); }
    Item item = arr[--size];
    arr[size] = null;

    // shrink size of array if necessary
    if (size > 0 && size == arr.length/4) { resize(arr.length/2); }
    return item;
  }

  public Iterator<Item> iterator() { return new ReverseArrayIterator(); }

  private class ReverseArrayIterator implements Iterator<Item> {
    private int i;

    public ReverseArrayIterator() {
      i = size - 1;
    }

    public boolean hasNext() { return i >= 0; }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) { throw new java.util.NoSuchElementException(); }
      return arr[i--];
    }
  }


  public static void main(String args[]) {
    ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
    String input = "to be or not to - be - - that - - - is";
    String[] arr = input.split(" ");

    // to be not that or be
    for (String s : arr) {
      if (s.equals("-")) System.out.print(stack.pop() + " ");
      else stack.push(s);
    }

    System.out.println();
    System.out.println(stack.isEmpty());
    System.out.println(stack.size());
  }
}
