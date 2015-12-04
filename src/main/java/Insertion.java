import java.util.Comparator;

/**
 * Created by yubo on 12/4/15.
 */
public class Insertion {

    private Insertion() {
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort(Object[] a, Comparator cmp) {
        int N = a.length - 1;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1], cmp); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static int[] indexSort(Comparable[] a) {
        int N = a.length;
        int[] indexes = new int[N];
        for (int i = 0; i < N; i++) {
            indexes[i] = i;
        }

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[indexes[j]], a[indexes[j - 1]]); j--) {
                exch(indexes, j, j - 1);
            }
        }
        return indexes;
    }

    /**
     * Help sorting utility functions
     */

    /**
     * determine a is less than b
     *
     * @param a Comparable instance
     * @param b Comparable instance
     * @return a < b true, otherwise false
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a, b) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Check if array is sorted - useful for debugging
     */
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length - 1, comparator);
    }

    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1], comparator)) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

}
