import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=1000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [78498];
	private static int PrimeCount=0;
	
	private static void sieve() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int countDiv(int n) {
		int count=0;
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			count++;
			while (n%Prime[i]==0) n/=Prime[i];
		}
		if (n>1) count++;
		return count;
	}

	public static void main(String[] args) throws Exception {
		sieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			System.out.printf("%d : %d\n",n,countDiv(n));
		}
	}

}
