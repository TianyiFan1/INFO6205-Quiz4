import java.security.Key;

/**
 * Implementation of a Binary Search Tree (BST) with key-value pairs.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // Root of BST

    private class Node {
        private Key key; // Sorted by key
        private Value val; // Associated data
        private Node left, right; // Left and right subtrees
        private int size; // Number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    // Returns number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * Inserts the specified key-value pair into the BST.
     * If the key already exists, update its value.
     * If the value is null, remove the key from the BST.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
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

    /**
     * Does this BST contain the given key?
     *
     * @param key the key
     * @return {@code true} if this BST contains {@code key}, {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null; // Reuse the get method to check existence
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if it exists, otherwise {@code null}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null; // Key not found
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key); // Search left subtree
        else if (cmp > 0) return get(x.right, key); // Search right subtree
        else return x.val; // Key found, return value
    }
}
