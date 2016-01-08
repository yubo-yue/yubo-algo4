package dynamicplan;

/**
 * Created by yubo on 1/7/16.
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 *
 * MaxSoFar = max(MaxEndingHere, MaxSoFar)
 * MaxEndingHere = max(MaxEndingHere + a[i], 0)
 */
public class LargestSubarraySum {

    public static Integer maxSubArraySum(Integer[] a) {
        Integer maxSoFar = 0;
        Integer maxEndingHere = 0;

        for (Integer e : a) {
            maxSoFar = Math.max(maxEndingHere, maxSoFar);
            maxEndingHere = Math.max(maxEndingHere + e, 0);
        }

        return maxSoFar;
    }


    public static void main(String[] args) {
        Integer[] a =  {-2, -3, 4, -1, -2, 1, 5, -3};
        int max = LargestSubarraySum.maxSubArraySum(a);
        System.out.println("Max subarray sum is : " + max);
    }
}
