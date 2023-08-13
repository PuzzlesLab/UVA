import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=31624; // sqrt(1000000000)

		boolean [] notPrime=new boolean [MAX];
		int [] primes=new int [3401];
		int primeCount=0;
		for (int i=2;i*i<MAX;i++) if (!notPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) notPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!notPrime[i]) primes[primeCount++]=i;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			if (n==1) {
				System.out.println(0);
				continue;
			}

			// Apply formula : https://en.wikipedia.org/wiki/Euler%27s_totient_function
			int result=n;
			for (int fi=0;fi<primeCount && primes[fi]*primes[fi]<=n;fi++) if (n%primes[fi]==0) {
				result/=primes[fi];
				result*=primes[fi]-1;
				while (n%primes[fi]==0) n/=primes[fi];
			}
			if (n>1) {
				result/=n;
				result*=n-1;
			}
			System.out.println(result);
		}
	}

}
