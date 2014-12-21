package searchingforpattern;

import java.nio.charset.Charset;

/**
 * Pattern matching algorithm, Rabin & Karp
 * @author m
 *
 */
public class RabinKarp {

	private static final int D = 256;
	/**
	 * 
	 * @param pat pattern character string
	 * @param txt
	 * @param q prime number
	 */
	public static void search (byte[] pat, byte[] txt, int q)
	{
		int M = pat.length;
		int N = txt.length;
		int p = 0, t = 0;
		//h is pow(d, m-1) % q
		int h = 1;
		
		for (int i = 0; i < M - 1; i++)
			h = (h * D) % q;
		
		for (int i = 0; i < M; i++)
		{
			p = (D * p + pat[i]) % q;
			t = (D * t + txt[i]) % q;
		}
		
		for (int i = 0; i <= N - M; i++) {
			if (p == t) {
				int j;
				for (j = 0; j < M; j++) {
					if (txt[i + j] != pat[j]) break;
				}
				if (j == M) {
					System.out.println("Pattern found at index " + i);
				}
			}
			
			//calculate hash value for next window of text; Removing leading digit, add trailing digit
			if (i < N - M) {
				t = (D * (t - txt[i] * h) + txt[i + M]) % q;
				if (t < 0)
					t = (t + q);
			}
		}
		
	}
	
	public static void main(String[] args) {
		String txt = "GEEKS FOR GEEKGEEK" ;
		String pat = "GEEK";
		int q = 101; //prime number
		RabinKarp.search(pat.getBytes(Charset.forName("US-ASCII")), txt.getBytes(Charset.forName("US-ASCII")), q);
	}
	
}
