package com.linbo.algs.datatypes;

import java.util.NoSuchElementException;

/**
 * Created by @linbojin on 2/2/17.
 *  This class represents an ordered symbol table of generic
 *  key-value pairs.
 *  This implementation uses an (unbalanced) binary search tree. It requires that
 *  the key type implements the Comparable interface and calls the
 *  compareTo() and method to compare two keys.
 *  Operations each take linear time in the worst case, if the tree becomes unbalanced.
 *  The size and is-empty operations take constant time.
 */
public class BST<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    private Key key;
    private Value val;
    private Node left, right;
    private int size;

    public Node(Key key, Value val, int size) {
      this.key = key;
      this.val = val;
      this.size = size;
    }
  }

  public BST() { }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size() { return size(root); }

  private int size(Node x) {
    if (x == null) return 0;
    else return x.size;
  }

  public boolean contains(Key key) {
    if (key == null) throw new java.lang.IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  public Value get(Key key) {
    return get(root, key);
  }

  private Value get(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else return x.val;
  }

  public void put(Key key, Value val) {
    if (key == null) throw new java.lang.IllegalArgumentException("first argument to put() is null");
    if (val == null) {
      delete(key);
      return;
    }
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    x.size = 1 + size(x.left) + size(x.right);
    return x;
  }

  public void deleteMin() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Symbol table underflow");
    root = deleteMin(root);
  }

  private Node deleteMin(Node x) {
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void deleteMax() {
    if (isEmpty()) throw new java.util.NoSuchElementException("Symbol table underflow");
    root = deleteMax(root);
  }

  private Node deleteMax(Node x) {
    if (x.right == null) return x.left;
    x.right = deleteMax(x.right);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument of delete() is null");
    root = delete(root, key);
  }

  private Node delete(Node x, Key key) {
    if (x == null) return null;

    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = delete(x.left, key);
    else if (cmp > 0) x.right = delete(x.right, key);
    else {
      if (x.left == null) return x.right;
      if (x.right == null) return x.left;
      Node t = x;
      x = min(t.right);
      x.right = deleteMin(t.right);
      x.left = t.left;
    }
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public Key min() {
    if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
    return min(root).key;
  }

  private Node min(Node x) {
    if (x.left == null) return x;
    else return min(x.left);
  }

  public Key max() {
    if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
    return max(root).key;
  }

  private Node max(Node x) {
    if (x.right == null) return x;
    else return max(x.right);
  }

  public Key floor(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to floor() is null");
    if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
    Node x = floor(root, key);
    if (x == null) return null;
    else return x.key;
  }

  private Node floor(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp <  0) return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
  }

  public Key ceiling(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
    if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
    Node x = ceiling(root, key);
    if (x == null) return null;
    else return x.key;
  }

  private Node ceiling(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0) {
      Node t = ceiling(x.left, key);
      if (t != null) return t;
      else return x;
    }
    return ceiling(x.right, key);
  }

  public Key select(int k) {
    if (k < 0 || k >= size()) throw new IllegalArgumentException();
    Node x = select(root, k);
    return x.key;
  }

  // Return key of rank k.
  private Node select(Node x, int k) {
    if (x == null) return null;
    int t = size(x.left);
    if      (t > k) return select(x.left,  k);
    else if (t < k) return select(x.right, k-t-1);
    else            return x;
  }

  public int rank(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");
    return rank(key, root);
  }

  // Number of keys in the subtree less than key.
  private int rank(Key key, Node x) {
    if (x == null) return 0;
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) return rank(key, x.left);
    else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
    else              return size(x.left);
  }

  public Iterable<Key> keysIterable() {
    return keysIterable(min(), max());
  }

  // Returns all keys in the symbol table in the given range.
  public Iterable<Key> keysIterable(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    keys(root, queue, lo, hi);
    return queue;
  }

  private void keys(Node x, LinkedQueue<Key> queue, Key lo, Key hi) {
    if (x == null) return;
    int cmplo = lo.compareTo(x.key);
    int cmphi = hi.compareTo(x.key);
    if (cmplo < 0) keys(x.left, queue, lo, hi);
    if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
    if (cmphi > 0) keys(x.right, queue, lo, hi);
  }

  public int size(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

    if (lo.compareTo(hi) > 0) return 0;
    if (contains(hi)) return rank(hi) - rank(lo) + 1;
    else              return rank(hi) - rank(lo);
  }


  // return the height of the BST (a 1-node tree has height 0)
  public int height() {
    return height(root);
  }
  private int height(Node x) {
    if (x == null) return -1;
    return 1 + Math.max(height(x.left), height(x.right));
  }

  // return the keys in the BST in level order traversal
  public Iterable<Key> levelOrder() {
    LinkedQueue<Key> keys = new LinkedQueue<Key>();
    LinkedQueue<Node> queue = new LinkedQueue<Node>();
    queue.enqueue(root);
    while (!queue.isEmpty()) {
      Node x = queue.dequeue();
      if (x == null) continue;
      keys.enqueue(x.key);
      queue.enqueue(x.left);
      queue.enqueue(x.right);
    }
    return keys;
  }

  public static void main(String[] args) {

    BST st = new BST();

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
