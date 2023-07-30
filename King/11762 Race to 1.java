import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int MAX=1000001;
	private static boolean [] NotPrime = new boolean [MAX];
	private static int [] Primes=new int [MAX];
	private static int PrimesCount=0;
	private static double [] Dp=new double [MAX];

	private static void sieveE() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Primes[PrimesCount++]=i;
	}

	private static double calc(int D) {
		if (D==1) return 0.0;

		if (Dp[D]<0.0) {
			double den=0.0;
			double num=0.0;
			for (int i=0;i<PrimesCount && Primes[i]<=D;i++) {
				num++;
				if (D%Primes[i]==0) {
					num+=calc(D/Primes[i]);
					den++;
				}
			}
			Dp[D]=num/den;
		}
		return Dp[D];
	}

	public static void main(String[] args) throws Exception {
		sieveE();
		Arrays.fill(Dp,-1.0);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			System.out.printf("Case %d: %.10f\n",t,calc(N));
		}
	}

}
