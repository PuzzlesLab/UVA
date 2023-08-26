import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

class Main {

	private static final int MAX=46341;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [4792];
	private static int PrimeCount;

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static HashMap<Integer,Integer> getPF(int n) {
		HashMap<Integer,Integer> pf=new HashMap<>();
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) {
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
		int TC=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			long ans=0;
			if (N==1) ans=2;
			else {
				HashMap<Integer,Integer> pf=getPF(N);
				for (Entry<Integer,Integer> entry: pf.entrySet()) {
					int f=entry.getKey();
					int pow=entry.getValue();
					long curr=1;
					while (pow>0) {
						curr*=f;
						pow--;
					}
					ans+=curr;
				}
				if (pf.size()==1) ans+=1;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb.toString());
		}
	}

}
