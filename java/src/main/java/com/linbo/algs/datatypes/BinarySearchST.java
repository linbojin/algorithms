package com.linbo.algs.datatypes;

/**
 * Created by @linbojin on 1/2/17.
 *  This class represents an ordered symbol table of generic
 *  key-value pairs.
 *  This implementation uses a sorted array. It requires that
 *  the key type implements the Comparable interface and calls the
 *  compareTo() and method to compare two keys.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
  private static final int INIT_CAPACITY = 2;
  private Key[] keys;
  private Value[] vals;
  private int n = 0;

  public BinarySearchST() { this(INIT_CAPACITY); }

  public BinarySearchST(int capacity) {
    keys = (Key[]) new Comparable[capacity];
    vals = (Value[]) new Object[capacity];
  }

  private void resize(int capacity) {
    assert capacity >= n;
    Key[] tempk = (Key[]) new Comparable[capacity];
    Value[] tempv = (Value[]) new Object[capacity];
    for (int i = 0; i < n; i++) {
      tempk[i] = keys[i];
      tempv[i] = vals[i];
    }
    keys = tempk;
    vals = tempv;
  }

  public int size() { return n; }

  public boolean isEmpty() { return size() == 0; }

  public boolean contains(Key key) {
    if (key == null) throw new java.lang.IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  public Value get(Key key) {
    if (key == null) throw new java.lang.IllegalArgumentException("argument to get() is null");
    if (isEmpty()) return null;
    int i = rank(key);
    if (i < n && keys[i].compareTo(key) == 0) return vals[i];
    return null;
  }

  public int rank(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");

    int lo = 0, hi = n-1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      int cmp = key.compareTo(keys[mid]);
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return lo;
  }

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");

    if (val == null) {
      delete(key);
      return;
    }

    int i = rank(key);

    // key is already in table
    if (i < n && keys[i].compareTo(key) == 0) {
      vals[i] = val;
      return;
    }

    // insert new key-value pair
    if (n == keys.length) resize(2*keys.length);

    for (int j = n; j > i; j--) {
      keys[j] = keys[j-1];
      vals[j] = vals[j-1];
    }
    keys[i] = key;
    vals[i] = val;
    n++;
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    if (isEmpty()) return;

    int i = rank(key);

    // key not in table
    if (i == n || keys[i].compareTo(key) != 0) {
      return;
    }

    for (int j = i; j < n-1; j++) {
      keys[j] = keys[j+1];
      vals[j] = vals[j+1];
    }

    n--;
    keys[n] = null;
    vals[n] = null;

    // resize if 1/4 full
    if (n > 0 && n == keys.length/4) resize(keys.length/2);

  }

  public Key min() {
    if (isEmpty()) return null;
    return keys[0];
  }

  public Key max() {
    if (isEmpty()) return null;
    return keys[n-1];
  }

  public Key select(int k) {
    if (k < 0 || k >= n) return null;
    return keys[k];
  }

  public Key floor(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to floor() is null");
    int i = rank(key);
    if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
    if (i == 0) return null;
    else return keys[i-1];
  }

  public Key ceiling(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
    int i = rank(key);
    if (i == n) return null;
    else return keys[i];
  }

  public int size(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

    if (lo.compareTo(hi) > 0) return 0;
    if (contains(hi)) return rank(hi) - rank(lo) + 1;
    else              return rank(hi) - rank(lo);
  }

  public Iterable<Key> keysIterable() {
    return keysIterable(min(), max());
  }

  public Iterable<Key> keysIterable(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    if (lo.compareTo(hi) > 0) return queue;
    for (int i = rank(lo); i < rank(hi); i++)
      queue.enqueue(keys[i]);
    if (contains(hi)) queue.enqueue(keys[rank(hi)]);
    return queue;
  }

  public static void main(String[] args) {

    BinarySearchST st = new BinarySearchST();

    st.put("A", 0);
    st.put("C", 2);
    st.put("I", 8);
    st.put("F", 5);
    st.put("D", 3);
    st.put("K", 10);
    st.put("J", 9);
    st.put("E", 4);
    st.put("H", 7);
    st.put("B", 1);
    st.put("G", 6);

    System.out.println(st.size());     // 11
    System.out.println(st.isEmpty());  // false
    System.out.println(st.get("J"));   // 9
    System.out.println(st.get("M"));   // null
    System.out.println(st.min());      // A
    System.out.println(st.max());      // K
    System.out.println(st.rank("F"));  // 5
    System.out.println(st.floor("O"));  // K
    System.out.println(st.ceiling("O"));  // null

    st.delete("A");
    st.delete("J");
    for (Object a: st.keysIterable()) {
      System.out.print(a + " ");
    }
  }
}
