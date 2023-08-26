import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static final int MAX=10000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static long [] Prime=new long [664579];
	private static int PrimeCount=0;

	private static void sieve() {
		for (int i=2;i*i<=MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static HashMap<Long,Integer> getPF(long n) {
		HashMap<Long,Integer> pf=new HashMap<>();
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) {
			int count=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				count++;
			}
			if (count==0) continue;
			pf.put(Prime[i],count);
		}
		if (n>1) pf.put(n,1);
		return pf;
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long N=Long.parseLong(br.readLine());
			if (N==1) break;

			HashMap<Long,Integer> pf=getPF(N);
			int ans=0;
			for (int v: pf.values()) ans+=v;
			System.out.println(ans);
		}
	}

}