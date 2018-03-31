package yubo.algo.common;

import java.util.Objects;
import java.util.Random;

public final class StdRandom {
    private static Random random;
    private static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    private StdRandom() {
    }

    public static void setSeed(long s) {
        seed = s;
        random = new Random(seed);
    }

    public static long getSeed() {
        return seed;
    }

    public static double uniform() {
        return random.nextDouble();
    }

    public static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);

        return random.nextInt(n);
    }

    public static long uniform(long n) {
        if (n <= 0L) throw new IllegalArgumentException();

        // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#longs-long-long-long-
        long r = random.nextLong();
        long m = n - 1;

        if ((n & m) == 0L) {
            return r & m;
        }

        // reject over-represented candidates
        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }

    public static int uniform(int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }

        return a + uniform(b - a);
    }

    public static double uniform(double a, double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform() * (b - a);
    }

    public static boolean bernoulli(double p) {
        if (!(p >= 0.0 && p <= 1.0))
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0: " + p);
        return uniform() < p;
    }

    public static boolean bernoulli() {
        return bernoulli(0.5);
    }

    public static double gaussian() {
        // use the polar form of the Box-Muller transform
        double r, x, y;
        do {
            x = uniform(-1.0, 1.0);
            y = uniform(-1.0, 1.0);
            r = x * x + y * y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);

        // Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
        // is an independent random gaussian
    }

    /**
     * Returns a random real number from a Gaussian distribution with mean &mu;
     * and standard deviation &sigma;.
     *
     * @param mu    the mean
     * @param sigma the standard deviation
     * @return a real number distributed according to the Gaussian distribution
     * with mean {@code mu} and standard deviation {@code sigma}
     */
    public static double gaussian(double mu, double sigma) {
        return mu + sigma * gaussian();
    }

    /**
     * Returns a random integer from a geometric distribution with success
     * probability <em>p</em>.
     *
     * @param p the parameter of the geometric distribution
     * @return a random integer from a geometric distribution with success
     * probability {@code p}; or {@code Integer.MAX_VALUE} if
     * {@code p} is (nearly) equal to {@code 1.0}.
     * @throws IllegalArgumentException unless {@code p >= 0.0} and {@code p <= 1.0}
     */
    public static int geometric(double p) {
        if (!(p >= 0.0 && p <= 1.0)) {
            throw new IllegalArgumentException("probability p must be between 0.0 and 1.0: " + p);
        }
        // using algorithm given by Knuth
        return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
    }


    /**
     * Returns a random integer from a Poisson distribution with mean &lambda;.
     *
     * @param lambda the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution with mean {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0} and not infinite
     */
    public static int poisson(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        if (Double.isInfinite(lambda))
            throw new IllegalArgumentException("lambda must not be infinite: " + lambda);
        // using algorithm given by Knuth
        // see http://en.wikipedia.org/wiki/Poisson_distribution
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform();
        } while (p >= expLambda);
        return k - 1;
    }

    /**
     * Returns a random real number from the standard Pareto distribution.
     *
     * @return a random real number from the standard Pareto distribution
     */
    public static double pareto() {
        return pareto(1.0);
    }

    /**
     * Returns a random real number from a Pareto distribution with
     * shape parameter &alpha;.
     *
     * @param alpha shape parameter
     * @return a random real number from a Pareto distribution with shape
     * parameter {@code alpha}
     * @throws IllegalArgumentException unless {@code alpha > 0.0}
     */
    public static double pareto(double alpha) {
        if (!(alpha > 0.0))
            throw new IllegalArgumentException("alpha must be positive: " + alpha);
        return Math.pow(1 - uniform(), -1.0 / alpha) - 1.0;
    }

    /**
     * Returns a random real number from the Cauchy distribution.
     *
     * @return a random real number from the Cauchy distribution.
     */
    public static double cauchy() {
        return Math.tan(Math.PI * (uniform() - 0.5));
    }


    /**
     * Returns a random integer from the specified discrete distribution.
     *
     * @param probabilities the probability of occurrence of each integer
     * @return a random integer from a discrete distribution:
     * {@code i} with probability {@code probabilities[i]}
     * @throws IllegalArgumentException if {@code probabilities} is {@code null}
     * @throws IllegalArgumentException if sum of array entries is not (very nearly) equal to {@code 1.0}
     * @throws IllegalArgumentException unless {@code probabilities[i] >= 0.0} for each index {@code i}
     */
    public static int discrete(double[] probabilities) {
        if (probabilities == null) throw new IllegalArgumentException("argument array is null");
        double EPSILON = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if (!(probabilities[i] >= 0.0))
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + probabilities[i]);
            sum += probabilities[i];
        }
        if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON)
            throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);

        // the for loop may not return a value when both r is (nearly) 1.0 and when the
        // cumulative sum is less than 1.0 (as a result of floating-point roundoff error)
        while (true) {
            double r = uniform();
            sum = 0.0;
            for (int i = 0; i < probabilities.length; i++) {
                sum = sum + probabilities[i];
                if (sum > r) return i;
            }
        }
    }

    /**
     * Returns a random integer from the specified discrete distribution.
     *
     * @param frequencies the frequency of occurrence of each integer
     * @return a random integer from a discrete distribution:
     * {@code i} with probability proportional to {@code frequencies[i]}
     * @throws IllegalArgumentException if {@code frequencies} is {@code null}
     * @throws IllegalArgumentException if all array entries are {@code 0}
     * @throws IllegalArgumentException if {@code frequencies[i]} is negative for any index {@code i}
     * @throws IllegalArgumentException if sum of frequencies exceeds {@code Integer.MAX_VALUE} (2<sup>31</sup> - 1)
     */
    public static int discrete(int[] frequencies) {
        if (frequencies == null) throw new IllegalArgumentException("argument array is null");
        long sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < 0)
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + frequencies[i]);
            sum += frequencies[i];
        }
        if (sum == 0)
            throw new IllegalArgumentException("at least one array entry must be positive");
        if (sum >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("sum of frequencies overflows an int");

        // pick index i with probabilitity proportional to frequency
        double r = uniform((int) sum);
        sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if (sum > r) return i;
        }

        // can't reach here
        assert false;
        return -1;
    }

    /**
     * Returns a random real number from an exponential distribution
     * with rate &lambda;.
     *
     * @param lambda the rate of the exponential distribution
     * @return a random real number from an exponential distribution with
     * rate {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0}
     */
    public static double exp(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        return -Math.log(1 - uniform()) / lambda;
    }


    public static void shuffle(Object[] a) {
        Objects.requireNonNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(double[] a) {
        Objects.requireNonNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(int[] a) {
        Objects.requireNonNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(char[] a) {
        Objects.requireNonNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(Object[] a, int lo, int hi) {
        Objects.requireNonNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(double[] a, int lo, int hi) {
        Objects.requireNonNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(int[] a, int lo, int hi) {
        Objects.requireNonNull(a);
        validateSubarrayIndices(lo, hi, a.length);

        for (int i = lo; i < hi; i++) {
            int r = i + uniform(hi - i);     // between i and hi-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static int[] permutation(int n, int k) {
        if (n < 0) throw new IllegalArgumentException("argument is negative");
        if (k < 0 || k > n) throw new IllegalArgumentException("k must be between 0 and n");
        int[] perm = new int[k];
        for (int i = 0; i < k; i++) {
            int r = uniform(i + 1);    // between 0 and i
            perm[i] = perm[r];
            perm[r] = i;
        }
        for (int i = k; i < n; i++) {
            int r = uniform(i + 1);    // between 0 and i
            if (r < k) perm[r] = i;
        }
        return perm;
    }

    public static int[] permutation(int n) {
        if (n < 0) throw new IllegalArgumentException("argument is negative");
        int[] perm = new int[n];
        for (int i = 0; i < n; i++)
            perm[i] = i;
        shuffle(perm);
        return perm;
    }

    private static void validateSubarrayIndices(int lo, int hi, int length) {
        if (lo < 0 || hi > length || lo > hi) {
            throw new IllegalArgumentException("subarray indices out of bounds: [" + lo + ", " + hi + ")");
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (args.length == 2) StdRandom.setSeed(Long.parseLong(args[1]));
        double[] probabilities = {0.5, 0.3, 0.1, 0.1};
        int[] frequencies = {5, 3, 1, 1};
        String[] a = "A B C D E F G".split(" ");

        StdOut.println("seed = " + StdRandom.getSeed());
        for (int i = 0; i < n; i++) {
            StdOut.printf("%2d ", uniform(100));
            StdOut.printf("%8.5f ", uniform(10.0, 99.0));
            StdOut.printf("%5b ", bernoulli(0.5));
            StdOut.printf("%7.5f ", gaussian(9.0, 0.2));
            StdOut.printf("%1d ", discrete(probabilities));
            StdOut.printf("%1d ", discrete(frequencies));
            StdOut.printf("%11d ", uniform(100000000000L));
            StdRandom.shuffle(a);
            for (String s : a)
                StdOut.print(s);
            StdOut.println();
        }
    }
}
