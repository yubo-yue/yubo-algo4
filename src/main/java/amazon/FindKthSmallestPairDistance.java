package amazon;

import lombok.NonNull;
import org.apache.commons.lang.Validate;

import java.util.Arrays;

/**
 * Given an integer array, return k-th smallest distance among all the pairs. The distance of a pair(A, B) defined as the absolute difference between A and B.
 * <p>
 * Example 1:
 * Input nums = [1, 3, 1], k = 1
 * Output: 0;
 * <p>
 * Explanation:
 * Here are all the pairs:
 * (1, 3) = 2
 * (1, 1) = 0
 * (3, 1) = 2
 * <p>
 * The the first distance pair is (1, 1), and its distance is 0.
 * <p>
 * if kth smallest pair distance doesn't exist, return -1 since if it exists, it must be greater or equal to 0.
 */
public class FindKthSmallestPairDistance {

    public static int findKthSmallestPairDistance(@NonNull int[] nums, int k) {
        Validate.isTrue(k >= 1);

        //let's sort it first, time complexity is O(nlogn)
        Arrays.sort(nums);



        return 0;
    }
}
