package com.linbo.algs.searchings;

import com.linbo.algs.datatypes.LinkedQueue;

/**
 * Created by @linbojin on 7/2/17.
 *  This class represents a symbol table of generic
 *  key-value pairs.
 *  This implementation uses a separate chaining hash table. It requires that
 *  the key type overrides the equals() and hashCode() methods.
 *  The expected time per put, contains, or remove
 *  operation is constant, subject to the uniform hashing assumption.
 *  <p>
 */
public class SeparateChainingHashST<Key, Value> {
  private static final int INIT_CAPACITY = 4;

  private int n;  // number of key-value paris
  private int m;  // hash table size
  private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables

  public SeparateChainingHashST() {
    this(INIT_CAPACITY);
  }

  public SeparateChainingHashST(int m) {
    this.m = m;
    st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
    for (int i = 0; i < m; i++)
      st[i] = new SequentialSearchST<Key, Value>();
  }

  // resize the hash table to have the given number of chains,
  // rehashing all of the keys
  private void resize(int chains) {
    SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
    for (int i = 0; i < m; i++) {
      for (Key key : st[i].keys()) {
        temp.put(key, st[i].get(key));
      }
    }
    this.m  = temp.m;
    this.n  = temp.n;
    this.st = temp.st;
  }

  // hash value between 0 and m-1
  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % m;
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
    int i = hash(key);
    return st[i].get(key);
  }

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");
    if (val == null) {
      delete(key);
      return;
    }

    // double table size if average length of list >= 10
    if (n >= 10*m) resize(2*m);

    int i = hash(key);
    if (!st[i].contains(key)) n++;
    st[i].put(key, val);
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");

    int i = hash(key);
    if (st[i].contains(key)) n--;
    st[i].delete(key);

    // halve table size if average length of list <= 2
    if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
  }

  public Iterable<Key> keys() {
    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    for (int i = 0; i < m; i++) {
      for (Key key : st[i].keys())
        queue.enqueue(key);
    }
    return queue;
  }

  public static void main(String[] args) {
    SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();

    st.put("a", 1);
    st.put("b", 2);
    st.put("a", 3);
    st.put("BB", 33);
    st.put("Aa", 66);

    System.out.println(st.size());    // 4
    System.out.println(st.get("Aa"));
    System.out.println(st.get("BB"));
    System.out.println(st.get("a"));
    System.out.println(st.get("b"));

    st.delete("b");
    System.out.println(st.get("b"));
  }

}
