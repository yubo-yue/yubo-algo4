package searchingforpattern;
/**
 * Native implementation for pattern search problem
 * @author m
 *
 */
public class Native {

	public static void patternSearch(char[] pat, char[] text)
	{
		int M = pat.length;
		int N = text.length;
		
		for (int i = 0; i < N - M; i++) {
			int j ;
			
			for (j = 0; j < M; j++) {
				if (text[i + j] != pat[j]) {
					break;
				}
			}
			if (j == M) {
				System.out.println("Pattern found at " + i);
			}
		}
	}
	
	public static void main(String[] args) {
		Native.patternSearch("AABA".toCharArray(), "AABAACAADAABAAABAA".toCharArray());
		
	}
}
