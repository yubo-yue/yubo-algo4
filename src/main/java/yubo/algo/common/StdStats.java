package yubo.algo.common;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang.Validate.isTrue;

/**
 * Library for statistics functions
 * <p>
 * The test client reads an array of real numbers from standard
 * input, and computes the minimum, mean, maximum, and
 * standard deviation.
 * <p>
 */
public final class StdStats {
    private StdStats() {
        // avoid use constructor to initiate instance of this class.
    }

    public static double max(double[] a) {
        nonNull(a);

        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < a.length; i++) {
            if (Double.isNaN(a[i])) return Double.NaN;
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public static double max(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        double max = Double.NEGATIVE_INFINITY;

        for (int i = lo; i < hi; i++) {
            if (Double.isNaN(a[i])) {
                return Double.NaN;
            }
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public static int max(int[] a) {
        nonNull(a);

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public static int max(int[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        int max = Integer.MIN_VALUE;
        for (int i = lo; i < hi; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        return max;
    }

    public static double min(double[] a) {
        nonNull(a);
        double min = Double.POSITIVE_INFINITY;

        for (int i = 0; i < a.length; i++) {
            if (Double.isNaN(a[i])) {
                return Double.NaN;
            }

            if (min > a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    public static double min(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        double min = Double.POSITIVE_INFINITY;

        for (int i = lo; i < hi; i++) {
            if (Double.isNaN(a[i])) {
                return Double.NaN;
            }

            if (min > a[i]) {
                min = a[i];
            }
        }

        return min;
    }

    public static int min(int[] a) {
        nonNull(a);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (min < a[i]) {
                min = a[i];
            }
        }

        return min;
    }

    public static int min(int[] a, int lo, int hi) {
        nonNull(a);

        int min = Integer.MAX_VALUE;
        for (int i = lo; i < hi; i++) {
            if (min < a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    public static double mean(double[] a) {
        nonNull(a);

        if (a.length == 0) {
            return Double.NaN;
        }

        double sum = sum(a);
        return sum / a.length;
    }

    public static double mean(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        int length = hi - lo;
        if (length == 0) return Double.NaN;

        double sum = sum(a, lo, hi);
        return sum / length;
    }

    public static double mean(int[] a) {
        nonNull(a);

        if (a.length == 0) return Double.NaN;
        int sum = sum(a);
        return 1.0 * sum / a.length;
    }

    /**
     * Sample variance, see http://mathworld.wolfram.com/SampleVariance.html
     *
     * @param a
     * @return sample variance
     */
    public static double var(double[] a) {
        nonNull(a);

        if (a.length == 0) {
            return Double.NaN;
        }
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }

        return sum / a.length - 1;
    }

    public static double var(int[] a) {
        nonNull(a);

        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }

    public static double var(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        int length = hi - lo;
        if (length == 0) return Double.NaN;

        double avg = mean(a, lo, hi);
        double sum = 0.0;
        for (int i = lo; i < hi; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (length - 1);
    }

    public static double varp(double[] a) {
        nonNull(a);

        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / a.length;
    }

    public static double varp(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo < hi);

        int length = hi - lo;
        if (length == 0) return Double.NaN;

        double avg = mean(a, lo, hi);
        double sum = 0.0;
        for (int i = lo; i < hi; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / length;
    }

    public static double stddev(double[] a) {
        nonNull(a);
        return Math.sqrt(var(a));
    }

    public static double stddev(int[] a) {
        nonNull(a);
        return Math.sqrt(var(a));
    }

    public static double stddev(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo < hi);

        return Math.sqrt(var(a, lo, hi));
    }

    public static double stddevp(double[] a) {
        nonNull(a);
        return Math.sqrt(varp(a));
    }

    public static double stddevp(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo < hi);

        return Math.sqrt(varp(a, lo, hi));
    }


    private static double sum(double[] a) {
        nonNull(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    private static double sum(double[] a, int lo, int hi) {
        nonNull(a);
        isTrue(lo <= hi);

        double sum = 0.0;
        for (int i = lo; i < hi; i++) {
            sum += a[i];
        }
        return sum;
    }

    private static int sum(int[] a) {
        nonNull(a);
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static void plotPoints(double[] a) {
        nonNull(a);
        int n = a.length;
        StdDraw.setXScale(-1, n);
        StdDraw.setPenRadius(1.0 / (3.0 * n));
        for (int i = 0; i < n; i++) {
            StdDraw.point(i, a[i]);
        }
    }

    public static void plotLines(double[] a) {
        nonNull(a);
        int n = a.length;
        StdDraw.setXScale(-1, n);
        StdDraw.setPenRadius();
        for (int i = 1; i < n; i++) {
            StdDraw.line(i-1, a[i-1], i, a[i]);
        }
    }

    public static void plotBars(double[] a) {
        nonNull(a);
        int n = a.length;
        StdDraw.setXScale(-1, n);
        for (int i = 0; i < n; i++) {
            StdDraw.filledRectangle(i, a[i]/2, 0.25, a[i]/2);
        }
    }

    public static void main(String[] args) {
        double[] a = StdArrayIO.readDouble1D();
        StdOut.printf("       min %10.3f\n", min(a));
        StdOut.printf("      mean %10.3f\n", mean(a));
        StdOut.printf("       max %10.3f\n", max(a));
        StdOut.printf("    stddev %10.3f\n", stddev(a));
        StdOut.printf("       var %10.3f\n", var(a));
        StdOut.printf("   stddevp %10.3f\n", stddevp(a));
        StdOut.printf("      varp %10.3f\n", varp(a));
    }

}
