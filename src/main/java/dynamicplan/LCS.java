package dynamicplan;
/**
 * Longest Common Sequence solved by dynamic programming.
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * @author m
 *
 */
public class LCS {

	public static int lcs(char[] a, char[] b) {
		int [][] L = new int[a.length + 1][b.length + 1];
		
		int i, j;
		
		/**
		 * Following steps build L[m+1][n+1] in bottom up fashion. Note
	     * that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] 
	     */
		for (i = 0; i <= a.length; i++) {
			for (j = 0; j <= b.length; j++) {
				if (i == 0 || j == 0) {
					L[i][j] = 0;
				}
				else if (a[i - 1] == b[j - 1]) {
					L[i][j] = L[i - 1][j - 1] + 1;
				}
				else {
					L[i][j] = max(L[i - 1][j], L[i][j - 1]);
				}
			}
		}
		
		int index = L[a.length][b.length];
		
		char[] str = new char[index];
		i = a.length; j = b.length;
		while ( i > 0 && j > 0) {
			if (a[i - 1] == b[j - 1]) {
				str[index - 1] = a[i - 1];
				i --; j --; index--;
			} else if (L[i - 1][j] < L[i][j - 1]) {
				j --;
			} else
				i --;
		}
		
		System.out.println("LCS is " + new String(str));
		
		return L[a.length][b.length];
	}
	
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	public static void main(String[] args) {
		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		
		int l = LCS.lcs(s1.toCharArray(), s2.toCharArray());
		System.out.println(l);
	}

}
