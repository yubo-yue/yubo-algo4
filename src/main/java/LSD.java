/**
 * Least Signifcant Digit string sorting
 * 
 * @author yubo
 * 
 */
public class LSD {

	public static void sort(String[] a, final int w) {
		int N = a.length;
		int R = 256; // extended ASCII character sets

		String[] aux = new String[N];

		for (int d = w - 1; d >= 0; d--) {
			int[] count = new int[R + 1];
			for (int i = 0; i < N; ++i)
				count[a[i].charAt(d) + 1]++;
			for (int r = 0; r < R; ++r)
				count[r + 1] += count[r];
			for (int i = 0; i < N; ++i)
				aux[count[a[i].charAt(d)]++] = a[i];
			for (int i = 0; i < N; ++i)
				a[i] = aux[i];
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		
		int W = a[0].length();
		sort(a, W);
		for (int i = 0; i < a.length; ++i)
			StdOut.println(a[i]);
	}

}
