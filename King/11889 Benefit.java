import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=10000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [664579];
	private static int PrimeCount;
	private static HashMap<Integer,HashMap<Integer,Integer>> Dp=new HashMap<>();

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static HashMap<Integer,Integer> getPF(int n) {
		int tempN=n;
		if (!Dp.containsKey(tempN)) {
			HashMap<Integer,Integer> pf=new HashMap<>();
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
			Dp.put(tempN,pf);
		}
		return Dp.get(tempN);
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			HashMap<Integer,Integer> pfA=getPF(A);
			HashMap<Integer,Integer> pfC=getPF(C);

			boolean ok=true;
			for (Entry<Integer,Integer> fAe: pfA.entrySet()) ok&=pfC.getOrDefault(fAe.getKey(),0)>=fAe.getValue();
			if (!ok) {
				System.out.println("NO SOLUTION");
				continue;
			}

			int sol=1;
			for (Entry<Integer,Integer> fCe: pfC.entrySet()) {
				int fC=fCe.getKey();
				int fCpow=fCe.getValue();
				int fApow=pfA.getOrDefault(fC,0);
				if (fApow!=fCpow) {
					int max=Math.max(fApow,fCpow);
					for (int i=0;i<max;i++) sol*=fC;
				}
			}
			System.out.println(sol);
		}
	}

}
