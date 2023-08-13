import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=100001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [9592];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static long sumDiv(long n) {
		long ans=1;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			long multi=Prime[fi];
			long total=1;
			while (n%Prime[fi]==0) {
				n/=Prime[fi];
				total+=multi;
				multi*=Prime[fi];
			}
			ans*=total;
		}
		return (n!=1)?ans*(n+1):ans;
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			long N=Long.parseLong(br.readLine());
			long sd=sumDiv(N)-N;
			if (N>sd) System.out.println("deficient");
			else if (N==sd) System.out.println("perfect");
			else System.out.println("abundant");
		}
	}

}
