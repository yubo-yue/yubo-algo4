package experiments.generic;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yubo on 12/27/15.
 */
public class Box<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

//    public static void main(String[] args) {
//        Box<Integer> integerBox = new Box<Integer>();
//        integerBox.set(new Integer(10));
//        integerBox.inspect(200f); // error: this is still String!
//    }

    @Test
    public void testGreaterThan() {
        Integer[] input = {1, 2, 3, 4, 5, 6, 9};
        int r = GenericMethods.countGreaterThan(input, 7);
        assertEquals(1, r);
    }

    @Test
    public void testBoxDemo() {

    }


}

class BoxDemo {

    public static <U> void addBox(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> u : boxes) {
            U boxContents = u.get();
            System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
            counter++;
        }
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class GenericMethods {
    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray) {
            if (e.compareTo(elem) > 0) {
                ++count;
            }
        }
        return count;
    }

    public static void process(List<? extends Number> nums) {
        for (Number n : nums) {
            System.out.println(n.doubleValue());
        }
    }

    public static double sum(List<? extends Number> nums) {
        double s = 0.0;
        for (Number n : nums) {
            s += n.doubleValue();
        }
        return s;
    }

    public static void addNumbers(List<? super Integer> nums) {
        for (int i = 0; i < 10; i ++) {
            nums.add(i);
        }
    }

}
