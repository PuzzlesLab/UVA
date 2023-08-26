import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static final int MAX=65537;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [6542];
	private static int PrimeCount;

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static HashMap<Integer,Integer> getPFTable(long n) {
		HashMap<Integer,Integer> pf=new HashMap<>();
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			int count=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				count++;
			}
			pf.put(Prime[i],pf.getOrDefault(Prime[i],0)+count);
		}
		if (n!=1) pf.put((int)n,1);
		return pf;
	}

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			long N=Integer.parseInt(s);
			HashMap<Integer,Integer> pFactors = getPFTable(Math.abs(N));

			int ans=0;
			for (int pf : pFactors.values()) {
				if (ans==0) ans=pf;
				else ans=gcd(ans,pf);
			}
			if (N<0) while ((ans&1)==0) ans>>=1; // Odd number power for negative N

			System.out.println(ans);
		}
	}

}