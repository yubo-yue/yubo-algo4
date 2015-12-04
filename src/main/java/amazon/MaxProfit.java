package amazon;

/**
 * Created by yubo on 11/27/15.
 * <p>
 * [Code] Give the algorithm to resolve the request and write the code that can be executed to output the result.
 * Given the historical share price, find out when to buy in and when to sale out that make the largest profile.
 * E.g. in the past five days, the share price is day 1: 100, day 2: 90, day 3: 120, day 4: 100, day 5: 130.
 * If we buy on day 2 and sale on day 5, the benefit is largest: 40.
 */
public class MaxProfit {


    public static int maxSum(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int max = 0;
        for (int num : nums) {
            ans = Math.max(ans, num + max);
            max = Math.max(0, max + num);
        }
        return ans;
    }

    /**
     * 100, 130, 120, 100, 90, 160
     * min  diff
     * 100, 0
     * 100, 30
     * 100, 30
     * 100, 30
     * 90, 30
     * 90, 70
     * <p>
     * initial phase min = a[0], maxDiff = 0
     * for each i, i > 0, maxDiff = max (a[i] - min, maxDiff); min = min(min, a[i])
     * invariant :
     *
     * @param a
     */
    public static int maxProfitByYubo(int a[]) {

        int min = a[0];
        int maxDiff = 0;

        for (int i = 1; i < a.length; i++) {
            maxDiff = Math.max(a[i] - min, maxDiff);
            min = Math.min(min, a[i]);
        }

        return maxDiff;
    }

    /**
     * 100, 130, 120, 100, 90, 160
     * ans, max
     * 0, -1000
     * 0, -100
     * 30, -100
     * 30, -100
     * 30, -100
     * 30, -90
     * 70, -90
     *
     * @param a
     * @return
     */
    public static int maxProfit(int[] a) {
        int ans = 0;
        int max = Integer.MAX_VALUE;
        for (int p : a) {
            ans = Math.max(p + max, ans);
            max = Math.max(max, -p);
        }
        return ans;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        int a[] = {100, 130, 120, 100, 90, 160};
        int ans = maxProfitByYubo(a);
        System.out.println("Answer is : " + ans);
    }
}
