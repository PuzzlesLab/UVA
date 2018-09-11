import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
    public static long gcd(long p, long q) {
        return (q == 0) ? p : gcd(q, p % q);
    }
    
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			long N=Integer.parseInt(st.nextToken()), K=Integer.parseInt(st.nextToken());
			K=Math.max(K, N-K);
			
			if (K==1) {
				System.out.println(N);
				continue;
			}
			long nMinusK=N-K;
			
			long nmk=1, fracnmk=1;
			long ans=1;
			for (long i=K+1;i<=N || nmk<=nMinusK;i++, nmk++) {
				if (i<=N) ans=ans*i;
				if (nmk<=nMinusK) fracnmk*=nmk;
				
				long gcd=gcd(ans, fracnmk);
				ans/=gcd;
				fracnmk/=gcd;
			}

			System.out.println(ans);
		}
	}

}