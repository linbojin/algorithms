package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 11/1/17.
 * This class represents a bag of generic items.
 * This implementation uses a singly-linked list with a non-static nested class for
 * linked-list nodes.
 */
public class LinkedBag<Item> implements Iterable<Item> {
  private int size;          // size of the stack
  private Node first;     // top of stack

  // helper linked list class
  // Memory: 16 + 8 + 4 + 8 + 4
  private class Node {
    private Item item;
    private Node next;
  }

  // create an empty stack
  public LinkedBag() {
    first = null;
    size = 0;
  }

  // is the stack empty?
  public boolean isEmpty() {
    return first == null;
  }

  // number of strings on the stack
  public int size() {
    return size;
  }

  // insert a new string onto stack
  public void add(Item item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    size++;
  }

  public Iterator<Item> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException("Stack is empty!");
      }
      Item item = current.item;
      current = current.next;
      return item;
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
