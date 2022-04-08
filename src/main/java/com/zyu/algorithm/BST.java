package com.zyu.algorithm;

import java.util.ArrayList;
import java.util.List;

public class BST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

    Node root;

    class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        // 以该节点为根的子树节点总数
        int N;
        // 红黑树中使用
        boolean color;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }


    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
    }

    protected void recalculateSize(Node x) {
        x.N = size(x.left) + size(x.right) + 1;
    }

    public void put(Key key, Value value) {

        root = put(root, key, value);

    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            x.val = value;
        if (cmp < 0)
            x.left = put(x.left, key, value);
        else
            x.right = put(x.right, key, value);
        recalculateSize(x);
        return x;

    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x.val;
        else if (cmp < 0)
            return get(x.left, key);
        else
            return get(x.right, key);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        return t != null ? t : x;
    }


    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x == null)
            return null;
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        recalculateSize(x);
        return x;
    }


    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        recalculateSize(x);
        return x;
    }

    public Key max() {
        return null;
    }

    public int rank(Key key) {

        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return size(x.left);
        else if (cmp < 0)
            return rank(x.left, key);
        else
            return size(x.left) + 1 + rank(x.right, key);
    }

    public List<Key> keys(Key l, Key h) {
        return keys(root,l,h);
    }

    private List<Key> keys(Node x, Key l, Key h) {
        List<Key> list = new ArrayList<Key>();
        if (x==null){
            return list;
        }

    }
}
