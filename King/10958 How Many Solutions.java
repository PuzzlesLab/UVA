import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=31628;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [3402];
	private static int PrimeCount=0;
	
	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int sumDiv(long n) {
		int ans=1;
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			int curr=1;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				curr++;
			}
			ans*=curr;
		}
		if (n>1) ans<<=1;
		return (ans<<1)-1;
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			long N=Integer.parseInt(st.nextToken());
			long M=Integer.parseInt(st.nextToken());
			long P=Integer.parseInt(st.nextToken());
			
			long multi=Math.abs(N*M*P*P);
			System.out.printf("Case %d: %d\n",tc++,sumDiv(multi));
		}
		System.out.print(sb.toString());
	}

}
