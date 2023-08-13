import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=1001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [168];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int divSum(int n) {
		int ans=1;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			int multi=Prime[fi];
			int total=1;
			while (n%Prime[fi]==0) {
				n/=Prime[fi];
				total+=multi;
				multi*=Prime[fi];
			}
			ans*=total;
		}
		if (n!=1) ans*=(n+1);
		return ans;
	}

	public static void main(String[] args) throws Exception {
		initialize();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			int v=divSum(N)-N;
			if (N<v) System.out.println("abundant");
			else if (N==v) System.out.println("perfect");
			else System.out.println("deficient");
		}
	}

}
