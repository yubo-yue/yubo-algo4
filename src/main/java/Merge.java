/**
 * Created by yubo on 12/4/15.
 */
public class Merge {

    private Merge() {
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //copy to aux array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Merge sort entry
     * @param a
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        sort(a, aux, 0, a.length - 1);
    }

    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) index[k] = aux[j++];
            else if (j > hi) index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else index[k] = aux[i++];
        }
    }


    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        merge(a, index, aux, lo, mid, hi);
    }

    public static int[] indexSort(Comparable[] a) {
        int N = a.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            index[i] = i;
        }
        int[] aux = new int[N];
        sort(a, index, aux, 0, N - 1);
        return index;
    }

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

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }


    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        show(a);
    }
}
