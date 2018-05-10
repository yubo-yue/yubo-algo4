package algorithm4.searching;

import java.util.Objects;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;


    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value value) {

    }

    public Value get(Key key) {
        return null;
    }

    public void delete(Key key) {
    }

    public boolean contains(Key key) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    Iterable<Key> keys() {
        return null;
    }
}
