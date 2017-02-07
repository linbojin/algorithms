package com.linbo.algs.searchings;

import com.linbo.algs.datatypes.LinkedQueue;

/**
 * Created by @linbojin on 7/2/17.
 * This class represents an (unordered)
 * symbol table of generic key-value pairs.
 * This implementation uses a singly-linked list and sequential search.
 * It relies on the {@code equals()} method to test whether two keys
 * are equal.
 */
public class SequentialSearchST<Key, Value> {
  private int n;           // number of key-value pairs
  private Node first;      // the linked list of key-value pairs

  // a helper linked list data type
  private class Node {
    private Key key;
    private Value val;
    private Node next;

    public Node(Key key, Value val, Node next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  public SequentialSearchST() {
  }

  public int size() {
    return n;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  public Value get(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    for (Node x = first; x != null; x = x.next) {
      if (key.equals(x.key))
        return x.val;
    }
    return null;
  }

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");
    if (val == null) {
      delete(key);
      return;
    }

    for (Node x = first; x != null; x = x.next) {
      if (key.equals(x.key)) {
        x.val = val;
        return;
      }
    }
    first = new Node(key, val, first);
    n++;
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    first = delete(first, key);
  }

  // delete key in linked list beginning at Node x
  // warning: function call stack too large if table is large
  private Node delete(Node x, Key key) {
    if (x == null) return null;
    if (key.equals(x.key)) {
      n--;
      return x.next;
    }
    x.next = delete(x.next, key);
    return x;
  }


  public Iterable<Key> keys() {
    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    for (Node x = first; x != null; x = x.next)
      queue.enqueue(x.key);
    return queue;
  }


  public static void main(String[] args) {

    SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
    st.put("a", 1);
    st.put("b", 2);

    System.out.println(st.get("b"));
    System.out.println(st.get("a"));
  }
}
