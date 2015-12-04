package amazon.dynamic;

/**
 * Created by yubo on 12/3/15.
 * <p>
 * Count number of ways to divide a number in 4 parts
 * Given a positive integer n, find number of ways to divide n in four parts or represent n as sum of four positive integers. Here n varies from 0 to 5000.
 * <p>
 * Examples:
 * <p>
 * Input:  n =  5
 * Output: 1
 * There is only one way (1, 1, 1, 2)
 * <p>
 * Input:  n =  6
 * Output: 2
 * There are two ways (1, 1, 1, 3) and
 * (1, 1, 2, 2)
 * <p>
 * Input:  n =  8
 * Output: 5
 * There are five ways (2, 2, 2, 2), (1, 1, 1, 5),
 * (1, 1, 3, 3), (1, 1, 2, 4) and (1, 2, 2, 3)
 */
public class DynamicCountWays {

    public static int countWaysBruteForcelly(int n, int p) {

        int cnt = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = j; k < n; k++) {
                    for (int l = k; l < n; l++) {
                        if (i + j + k + l == n) {
                            System.out.println(i + " " + j + " " + k + " " + l);
                            cnt++;
                        }
                    }
                }
            }
        }

        return cnt;
    }


    /**
     * n is the number , p is count of partitions
     * f(n, p) = f(n - p, p) + f(n - 1, p - 1)
     *
     * f(n - 1, p - 1) all the partitions includes 1 element
     * f(n - p, p) all the partitions not include 1 element.
     * @param n
     * @param p
     * @return
     */

    public static int countWaysDynamically(int n, int p) {
        int result = 0;

        if (n == 0 && p == 0) return 1;
        if (p > n || (n > 0 && p == 0)) return 0;
        result = result + countWaysDynamically(n - p, p) + countWaysDynamically(n - 1, p - 1);
        return result;
    }


    public static int countWaysDynamicallyNonRecursive(int n, int p) {
        int [][] lookup = new int[n + 1][p + 1];
        lookup[0][0] = 1;

        System.out.println("[0,0] = " + lookup[0][0]);

        for (int j = 1; j <= p; j++) {
            for (int i = 1; i <= n; i++) {
                if (! (j > i || (i > 0 && j == 0))) {
                    lookup[i][j] = lookup[i - j][j] + lookup[i - 1][j - 1];
                    System.out.println("[" + i + "," + j + "] = " + lookup[i][j]);
                }
            }
        }
        return lookup[n][p];
    }

    public static void main(String[] args) {
        int r1 = countWaysBruteForcelly(6, 4);
        int r2 = countWaysDynamically(6, 4);
        int r3 = countWaysDynamicallyNonRecursive(6, 4);
        System.out.println("r1 = " + r1 + ", r2 = " + r2 + ", r3 = " + r3);

    }
}
