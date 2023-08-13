import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=1000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Primes=new int [78498];
	private static int PrimesCount=0;
	private static int [] PrimeFactors=new int [MAX];
	private static int [] Ans=new int [MAX];

	
	private static int countPrimeFactors(int n) {
		if (n==1) return 0;
		if (!NotPrime[n]) return 1;
		if (PrimeFactors[n]==0) {
			for (int fi=0;Primes[fi]<=n;fi++) if (n%Primes[fi]==0) {
				PrimeFactors[n]=1+countPrimeFactors(n/Primes[fi]);
				break;
			}
		}
		return PrimeFactors[n];
	}
	
	private static int calc(int n) {
		if (n==1) return 0;
		if (!NotPrime[n]) return 1+calc(n-1);
		if (Ans[n]==0) Ans[n]=calc(n-1)+countPrimeFactors(n);
		return Ans[n];
	}

	public static void main(String[] args) throws Exception {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Primes[PrimesCount++]=i;
		for (int i=2;i<MAX;i++) { // Calc all cases.
			countPrimeFactors(i);
			calc(i);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.println(calc(N));
		}
	}

}