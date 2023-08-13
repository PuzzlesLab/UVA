import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=31624; // sqrt(1000000000)
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Primes=new int [3401];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Primes[PrimeCount++]=i;
	}

	private static int calc(int n) {
		// Apply formula : https://en.wikipedia.org/wiki/Euler%27s_totient_function
		int result=n;
		for (int fi=0;fi<PrimeCount && Primes[fi]*Primes[fi]<=n;fi++) if (n%Primes[fi]==0) {
			result/=Primes[fi];
			result*=Primes[fi]-1;
			while (n%Primes[fi]==0) n/=Primes[fi];
		}
		if (n>1) {
			result/=n;
			result*=n-1;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		initialize();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			System.out.println(calc(N));
		}
	}

}
