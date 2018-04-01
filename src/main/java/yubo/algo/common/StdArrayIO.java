package yubo.algo.common;

public final class StdArrayIO {

    private StdArrayIO() {
    }

    public static double[] readDouble1D() {
        int n = StdIn.readInt();

        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readDouble();
        }

        return a;
    }

    public static void print(double[] a) {
        int n = a.length;
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%9.5f ", a[i]);
        }
        StdOut.println();
    }

    public static double[][] readDouble2D() {
        int m = StdIn.readInt();
        int n = StdIn.readInt();
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readDouble();
            }
        }
        return a;
    }

    public static void print(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        StdOut.println(m + " " + n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                StdOut.printf("%9.5f ", a[i][j]);
            }
            StdOut.println();
        }
    }

    public static int[] readInt1D() {
        int n = StdIn.readInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readInt();
        }

        return a;
    }

    public static void print(int[] a) {
        int n = a.length;
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%9d ", a[i]);
        }
        StdOut.println();
    }

    public static int[][] readInt2D() {
        int m = StdIn.readInt();
        int n = StdIn.readInt();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readInt();
            }
        }
        return a;
    }

    public static void print(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        StdOut.println(m + " " + n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                StdOut.printf("%9d ", a[i][j]);
            }
            StdOut.println();
        }
    }

    public static boolean[] readBoolean1D() {
        int n = StdIn.readInt();
        boolean[] a = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readBoolean();
        }
        return a;
    }

    public static void print(boolean[] a) {
        int n = a.length;
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            if (a[i]) StdOut.print("1 ");
            else StdOut.print("0 ");
        }
        StdOut.println();
    }

    public static boolean[][] readBoolean2D() {
        int m = StdIn.readInt();
        int n = StdIn.readInt();
        boolean[][] a = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readBoolean();
            }
        }
        return a;
    }

    public static void print(boolean[][] a) {
        int m = a.length;
        int n = a[0].length;
        StdOut.println(m + " " + n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]) StdOut.print("1 ");
                else StdOut.print("0 ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {

        // read and print an array of doubles
        double[] a = StdArrayIO.readDouble1D();
        StdArrayIO.print(a);
        StdOut.println();

        // read and print a matrix of doubles
        double[][] b = StdArrayIO.readDouble2D();
        StdArrayIO.print(b);
        StdOut.println();

        // read and print a matrix of doubles
        boolean[][] d = StdArrayIO.readBoolean2D();
        StdArrayIO.print(d);
        StdOut.println();
    }
}
