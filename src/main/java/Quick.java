/**
 * Created by yubo on 12/10/15.
 */
public class Quick {


    private final static int CUT_OFF = 15;

    private Quick() {
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);

        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int hi) {
        if (hi <= low + CUT_OFF) {
            Insertion.sort(a, low, hi);
            return;
        }

        int j = partition(a, low, hi);
        sort(a, low, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int low, int hi) {
        int i = low;
        int j = hi + 1;

        Comparable v = a[i];
        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == low) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, low, j);

        return j;
    }

    /***************************************************************************
     * Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     * Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }


    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);

        // shuffle
        StdRandom.shuffle(a);
    }


}
