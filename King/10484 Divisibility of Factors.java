import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

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

	private static void addPFTable(int n, HashMap<Integer,Integer> pf) {
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			int count=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				count++;
			}
			pf.put(Prime[i],pf.getOrDefault(Prime[i],0)+count);
		}
		if (n!=1) pf.put(n,pf.getOrDefault(n,0)+1);
	}

	public static void main(String[] args) throws Exception {
		sieve();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int D=Math.abs(Integer.parseInt(st.nextToken()));
			if (N==0 && D==0) break;

			HashMap<Integer,Integer> tableN=new HashMap<>();
			for (int n=2;n<=N;n++) addPFTable(n,tableN);

			HashMap<Integer,Integer> tableD=new HashMap<>();
			addPFTable(D,tableD);

			if (tableN.equals(tableD)) { // N!=D, significant speedup!
				System.out.println(1);
				continue;
			}

			boolean fulfillMin=true;
			for (Entry<Integer,Integer> entry: tableD.entrySet()) {
				fulfillMin&=tableN.getOrDefault(entry.getKey(),0)>=entry.getValue();
				if (!fulfillMin) break;
			}
			if (!fulfillMin) {
				System.out.println(0);
				continue;
			}

			long ans=1;
			for (Entry<Integer,Integer> entry: tableN.entrySet()) {
				int pow=entry.getValue()-tableD.getOrDefault(entry.getKey(),0);
				ans*=(pow+1);
			}

			System.out.println(ans);
		}
	}

}
