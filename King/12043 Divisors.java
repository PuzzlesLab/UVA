import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=100001;
	private static boolean [] NotPrime=new boolean[MAX];
	private static long [] Prime=new long [9592];
	private static int PrimeCount=0;
	private static long [] NumDivDP=new long [MAX];
	private static long [] SumDivDP=new long [MAX];

	private static void sieve() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static long numDiv(int n) {
		int temp=n;
		if (NumDivDP[temp]==0) {
			long ans=1;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
				int pow=1;
				while (n%Prime[i]==0) {
					n/=Prime[i];
					pow++;
				}
				ans*=pow;
			}
			NumDivDP[temp] = (n!=1) ? ans<<1 : ans;
		}
		return NumDivDP[temp];
	}

	private static long sumDiv(int n) {
		int temp=n;
		if (SumDivDP[temp]==0) {
			long ans=1;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
				long multi=Prime[i];
				int total=1;
				while (n%Prime[i]==0) {
					n/=Prime[i];
					total+=multi;
					multi*=Prime[i];
				}
				ans*=total;
			}
			if (n!=1) ans*=(n+1);
			SumDivDP[temp]=ans;
		}
		return SumDivDP[temp];
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			
			long d=0;
			long h=0;
			for (int i=a;i<=b;i++) if (i%k==0) {
				d+=numDiv(i);
				h+=sumDiv(i);
			}
			System.out.printf("%d %d\n",d,h);
		}
	}

}
