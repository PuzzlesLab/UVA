import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=10000;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [1229];
	private static int PrimeCount;

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static void addPF(int n, HashMap<Integer,Integer> pf) {
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) {
			int pow=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				pow++;
			}
			if (pow==0) continue;
			pf.put(Prime[i],pf.getOrDefault(Prime[i],0)+pow);
		}
		if (n>1) {
			pf.put(n,pf.getOrDefault(n,0)+1);
		}
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			HashMap<Integer,Integer> pfM=new HashMap<>();
			addPF(M,pfM);
			HashMap<Integer,Integer> pfN=new HashMap<>();
			for (int n=2;n<=N;n++) addPF(n,pfN);

			int min=Integer.MAX_VALUE;
			for (Entry<Integer,Integer> entry: pfM.entrySet()) min=Math.min(min,pfN.getOrDefault(entry.getKey(),0)/entry.getValue());
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(":\n");
			sb.append(min==0?"Impossible to divide":min);
			System.out.println(sb.toString());
		}
	}

}
