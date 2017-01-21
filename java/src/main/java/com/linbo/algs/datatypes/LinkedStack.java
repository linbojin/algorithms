package com.linbo.algs.datatypes;

import java.util.Iterator;

/**
 * Created by @linbojin on 11/1/17.
 * This class represents a last-in-first-out (LIFO) stack of generic items.
 * This implementation uses a singly-linked list with a non-static nested class for
 * linked-list nodes.
 */
public class LinkedStack<Item> implements Iterable<Item> {
  private int size;          // size of the stack
  private Node first;     // top of stack

  // helper linked list class
  // Memory: 16 + 8 + 4 + 8 + 4
  private class Node {
    private Item item;
    private Node next;
  }

  // create an empty stack
  public LinkedStack() {
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
  public void push(Item item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    size++;
  }

  public Item peek() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
    return first.item;
  }

  // remove and return the string most recently added
  public Item pop() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Stack underflow");
    Item item = first.item;
    first = first.next;
    size--;
    return item;
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
      throw new java.lang.UnsupportedOperationException();
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
    LinkedStack<String> stack = new LinkedStack<String>();
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
