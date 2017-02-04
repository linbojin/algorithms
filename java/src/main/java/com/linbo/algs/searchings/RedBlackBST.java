package com.linbo.algs.searchings;

import com.linbo.algs.datatypes.LinkedQueue;

import java.util.NoSuchElementException;

/**
 * Created by @linbojin on 4/2/17.
 *  This class represents an ordered symbol table of generic
 *  key-value pairs.
 *  This implementation uses a left-leaning red-black BST. It requires that
 *  the key type implements the Comparable interface and calls the
 *  compareTo() and method to compare two keys.
 *  The put, contains, remove, minimum, maximum, ceiling, and floor operations each take
 *  logarithmic time in the worst case, if the tree becomes unbalanced.
 *  The size, and is-empty operations take constant time.
 *  Construction takes constant time.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  private Node root;

  private class Node {
    private Key key;
    private Value val;
    private Node left, right;
    private boolean color;  // color of parent link
    private int size;

    public Node(Key key, Value val, boolean color, int size) {
      this.key = key;
      this.val = val;
      this.color = color;
      this.size = size;
    }
  }

  public RedBlackBST() { }

  private boolean isRed(Node x) {
    if (x == null) return false;
    return x.color == RED;
  }

  private int size(Node x) {
    if (x == null) return 0;
    return x.size;
  }

  public int size() { return size(root); }

  public boolean isEmpty() { return root == null; }


  /***************************************************************************
   *  Standard BST search.
   ***************************************************************************/

  public Value get(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    return get(root, key);
  }

  private Value get(Node x, Key key) {
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0) x = x.left;
      else if (cmp > 0) {
        x = x.right;
      }
      else return x.val;
    }
    return null;
  }

  public boolean contains(Key key) { return get(key) != null; }

  /***************************************************************************
   *  Red-black tree insertion.
   ***************************************************************************/

  public void put(Key key, Value val) {
    if (key == null) throw new IllegalArgumentException("first argument to put() is null");
    if (val == null) {
      delete(key);
      return;
    }

    root = put(root, key, val);
    root.color = BLACK;
  }

  private Node put(Node h, Key key, Value val) {
    if (h == null) return new Node(key, val, RED, 1);

    int cmp = key.compareTo(h.key);
    if      (cmp < 0) h.left = put(h.left, key, val);
    else if (cmp > 0) h.right = put(h.right, key, val);
    else              h.val = val;

    // fix-up any right-leaning links
    if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
    if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
    if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
    h.size = size(h.left) + size(h.right) + 1;

    return h;
  }

  /***************************************************************************
   *  Red-black tree deletion.
   ***************************************************************************/
  public void deleteMin() {
    if (isEmpty()) throw new NoSuchElementException("BST underflow");

    // if both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMin(root);
    if (!isEmpty()) root.color = BLACK;
    // assert check();
  }

  // delete the key-value pair with the minimum key rooted at h
  private Node deleteMin(Node h) {
    if (h.left == null)
      return null;

    if (!isRed(h.left) && !isRed(h.left.left))
      h = moveRedLeft(h);

    h.left = deleteMin(h.left);
    return balance(h);
  }

  public void deleteMax() {
    if (isEmpty()) throw new NoSuchElementException("BST underflow");

    // if both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMax(root);
    if (!isEmpty()) root.color = BLACK;
    // assert check();
  }

  // delete the key-value pair with the maximum key rooted at h
  private Node deleteMax(Node h) {
    if (isRed(h.left))
      h = rotateRight(h);

    if (h.right == null)
      return null;

    if (!isRed(h.right) && !isRed(h.right.left))
      h = moveRedRight(h);

    h.right = deleteMax(h.right);

    return balance(h);
  }

  public void delete(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to delete() is null");
    if (!contains(key)) return;

    // if both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = delete(root, key);
    if (!isEmpty()) root.color = BLACK;
    // assert check();
  }

  // delete the key-value pair with the given key rooted at h
  private Node delete(Node h, Key key) {
    // assert get(h, key) != null;

    if (key.compareTo(h.key) < 0)  {
      if (!isRed(h.left) && !isRed(h.left.left))
        h = moveRedLeft(h);
      h.left = delete(h.left, key);
    }
    else {
      if (isRed(h.left))
        h = rotateRight(h);
      if (key.compareTo(h.key) == 0 && (h.right == null))
        return null;
      if (!isRed(h.right) && !isRed(h.right.left))
        h = moveRedRight(h);
      if (key.compareTo(h.key) == 0) {
        Node x = min(h.right);
        h.key = x.key;
        h.val = x.val;
        // h.val = get(h.right, min(h.right).key);
        // h.key = min(h.right).key;
        h.right = deleteMin(h.right);
      }
      else h.right = delete(h.right, key);
    }
    return balance(h);
  }


  /***************************************************************************
   *  Red-black tree helper functions.
   ***************************************************************************/

  private Node rotateLeft(Node h) {
    assert (h != null) && isRed(h.right);
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color;
    h.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }

  private Node rotateRight(Node h) {
    assert (h != null) && isRed(h.left);
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color;
    h.color = RED;
    x.size = h.size;
    h.size = size(h.left) + size(h.right) + 1;
    return x;
  }

  private void flipColors(Node h) {
    // h must have opposite color of its two children
    assert (h != null) && (h.left != null) && (h.right != null);
    assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }

  // Assuming that h is red and both h.left and h.left.left
  // are black, make h.left or one of its children red.
  private Node moveRedLeft(Node h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

    flipColors(h);
    if (isRed(h.right.left)) {
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      flipColors(h);
    }
    return h;
  }

  // Assuming that h is red and both h.right and h.right.left
  // are black, make h.right or one of its children red.
  private Node moveRedRight(Node h) {
    // assert (h != null);
    // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
    flipColors(h);
    if (isRed(h.left.left)) {
      h = rotateRight(h);
      flipColors(h);
    }
    return h;
  }

  // restore red-black tree invariant
  private Node balance(Node h) {
    // assert (h != null);

    if (isRed(h.right))                      h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
    if (isRed(h.left) && isRed(h.right))     flipColors(h);

    h.size = size(h.left) + size(h.right) + 1;
    return h;
  }

  public int height() {
    return height(root);
  }
  private int height(Node x) {
    if (x == null) return -1;
    return 1 + Math.max(height(x.left), height(x.right));
  }

  /***************************************************************************
   *  Ordered symbol table methods.
   ***************************************************************************/

  public Key min() {
    if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
    return min(root).key;
  }

  // the smallest key in subtree rooted at x; null if no such key
  private Node min(Node x) {
    // assert x != null;
    if (x.left == null) return x;
    else                return min(x.left);
  }

  public Key max() {
    if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
    return max(root).key;
  }

  // the largest key in the subtree rooted at x; null if no such key
  private Node max(Node x) {
    // assert x != null;
    if (x.right == null) return x;
    else                 return max(x.right);
  }

  public Key floor(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to floor() is null");
    if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
    Node x = floor(root, key);
    if (x == null) return null;
    else           return x.key;
  }

  // the largest key in the subtree rooted at x less than or equal to the given key
  private Node floor(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0)  return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else           return x;
  }

  public Key ceiling(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
    if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
    Node x = ceiling(root, key);
    if (x == null) return null;
    else           return x.key;
  }

  // the smallest key in the subtree rooted at x greater than or equal to the given key
  private Node ceiling(Node x, Key key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp > 0)  return ceiling(x.right, key);
    Node t = ceiling(x.left, key);
    if (t != null) return t;
    else           return x;
  }

  // Return the kth smallest key in the symbol table.
  public Key select(int k) {
    if (k < 0 || k >= size()) throw new IllegalArgumentException();
    Node x = select(root, k);
    return x.key;
  }

  // the key of rank k in the subtree rooted at x
  private Node select(Node x, int k) {
    // assert x != null;
    // assert k >= 0 && k < size(x);
    int t = size(x.left);
    if      (t > k) return select(x.left,  k);
    else if (t < k) return select(x.right, k-t-1);
    else            return x;
  }

  // Return the number of keys in the symbol table strictly less than {@code key}.
  public int rank(Key key) {
    if (key == null) throw new IllegalArgumentException("argument to rank() is null");
    return rank(key, root);
  }

  // number of keys less than key in the subtree rooted at x
  private int rank(Key key, Node x) {
    if (x == null) return 0;
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) return rank(key, x.left);
    else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
    else              return size(x.left);
  }

  /***************************************************************************
   *  Range count and range search.
   ***************************************************************************/

  public Iterable<Key> keysIterable() {
    if (isEmpty()) return new LinkedQueue<Key>();
    return keysIterable(min(), max());
  }

  public Iterable<Key> keysIterable(Key lo, Key hi) {
    if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
    if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

    LinkedQueue<Key> queue = new LinkedQueue<Key>();
    // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
    keys(root, queue, lo, hi);
    return queue;
  }

  // add the keys between lo and hi in the subtree rooted at x
  // to the queue
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

  public static void main(String[] args) {

    RedBlackBST st = new RedBlackBST<String, Integer>();

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

    st.deleteMin();
    st.deleteMax();
    st.delete("E");
    st.delete("J");
    for (Object a: st.keysIterable()) {
      System.out.print(a + " ");
    }
  }

}
