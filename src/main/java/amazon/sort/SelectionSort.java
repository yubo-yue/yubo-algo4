package amazon.sort;

/**
 * Created by yubo on 11/27/15.
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            int t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }
}
