package tree;

/**
 * Created by yubo on 1/5/16.
 */
public class RedBlackBST<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;
        private int N;

        public Node(K key, V value, boolean color, int N) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.N = N;
        }
    }

    public RedBlackBST() {

    }

    /**
     * Node helper functions
     */

    /**
     * Return the color of <code>Node</code>
     *
     * @param x Node
     * @return true if red, false black
     */
    private boolean isRed(Node x) {
        if (x == null) return BLACK;
        return x.color;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return null == root;
    }


    /**
     * @param key not null.
     * @return value if exist, otherwise null returned
     */
    public V get(K key) {
        if (null == key) throw new NullPointerException();
        return get(root, key);
    }

    private V get(Node n, K key) {
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return n.value;
        }

        return null;
    }

    public boolean contains(K key) {

        return get(key) != null;

    }


    public void put(K key, V value) {
        if (key == null) throw new NullPointerException("first argument to put() is null");

        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) return new Node(key, value, RED, 1);

        int cmp = key.compareTo(h.key);

        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.left)) flipColors(h);

        h.N = size(h.left) + size(h.left) + 1;
        return h;
    }

    /**
     * Red and Red in a row
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;

    }

    /**
     * Right leaning
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }


}
