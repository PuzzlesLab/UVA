import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			long T=Integer.parseInt(s);
			long A=1;
			long N=-1;
			/*
			 * Permutate n, with AP summation formula.
			 * 
			 * n
			 * - (2a + n-1) = T
			 * 2
			 * 
			 * 2an + n*n - n = 2T
			 * 2an = 2T + n*n - n
			 * 
			 *     2T + n*n - n
			 * a = -------------
			 *          2n
			 *          
			 * 
			 * When a = 1, n will be max:
			 *   
			 *  2an = 2T + n*n - n
			 *  2n = 2T + n*n - n
			 *  n*n + n = 2T
			 *  n(n + 1) = 2T
			 *  n = sqrt(2T), n+1 = sqrt(2T)
			 *  n = sqrt(2T) / sqrt(2T) - 1,
			 *  
			 *  We take the larger value, so n = sqrt(2T)
			 * 
			 */
			for (long n=(long)Math.sqrt(2*T);n>0;n--) {
				long test=2*T-n*n+n;
				long twon=2*n;
				if (test%twon==0 && test/twon>0) {
					A=test/twon;
					N=n;
					break;
				}
			}
			System.out.printf("%d = %d + ... + %d\n",T,A,A+(N-1));
		}
	}

}
