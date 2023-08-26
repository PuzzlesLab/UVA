import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=1<<20;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [82025];
	private static int PrimeCount;

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}
	
	private static void addPFTable(int n, HashMap<Integer,Integer> pf) {
		int f=1;
		for (int i=0;i<Prime.length && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			int count=0;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				f=Prime[i];
				count++;
			}
			pf.put(Prime[i],pf.getOrDefault(Prime[i],0)+count);
		}
		if (n>1) {
			f=n;
			pf.put(f,pf.getOrDefault(f,0)+1);
		}
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());

			HashMap<Integer,Integer> tableN=new HashMap<>();
			for (int n=2;n<=N;n++) addPFTable(n,tableN);
			HashMap<Integer,Integer> tableB=new HashMap<>();
			addPFTable(B,tableB);
			int zeroCount=Integer.MAX_VALUE;
			for (Entry<Integer,Integer> entry: tableB.entrySet()) {
				zeroCount=Math.min(zeroCount,tableN.getOrDefault(entry.getKey(),0)/entry.getValue());
				if (zeroCount==0) break;
			}

			double digits=0.0;
			for (int n=1;n<=N;n++) digits+=Math.log10(n);
			digits/=Math.log10(B);
			digits+=1;
			int digitsI=(int)digits;


			System.out.println(zeroCount+" "+digitsI);
		}
	}

}
