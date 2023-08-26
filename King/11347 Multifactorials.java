import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

class Main {

	private static final long INF=1000000000000000000L;
	private static final int MAX=1001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [168];
	private static int PrimeCount;

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static void addPF(int n, HashMap<Integer,Integer> pf) {
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) {
			int count=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				count++;
			}
			if (count==0) continue;
			pf.put(Prime[i],pf.getOrDefault(Prime[i],0)+count);
		}
		if (n>1) pf.put(n,pf.getOrDefault(n,0)+1);
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			String s=br.readLine();
			
			int num=0;
			int k=0;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (Character.isDigit(c)) num=num*10+(c-'0');
				else if (c=='!') k++;
			}
			
			int start=num%k==0 ? k : num%k;
			HashMap<Integer,Integer> pf=new HashMap<>();
			for (int i=start;i<=num;i+=k) addPF(i,pf);

			long ans=1;
			for (Entry<Integer,Integer> e: pf.entrySet()) {
				ans*=(e.getValue()+1);
				if (ans>INF) {
					ans=Long.MAX_VALUE;
					break;
				}
			}
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans==Long.MAX_VALUE ? "Infinity" : ans);
			System.out.println(sb.toString());
		}
	}

}