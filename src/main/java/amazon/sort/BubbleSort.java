package amazon.sort;

/**
 * Created by yubo on 11/27/15.
 */
public class BubbleSort {

    public static void swap(int i, int j, int[] arr) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void sort(int[] arr) {
        int l = 1;
        boolean skipRest = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (skipRest) break;
            skipRest = true;
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                    skipRest = false;
                }
        }
    }

    public static void printArr(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 2, 8};

        sort(arr);

        printArr(arr);

    }
}
