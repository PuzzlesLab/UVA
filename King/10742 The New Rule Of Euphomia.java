import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static boolean [] notPrime=new boolean[1000001];
	private static int [] primes=new int [300000];
	private static int primesMax=0;
	
	private static void sieve() {
		notPrime[0]=true;
		notPrime[1]=true;
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) {
			for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		}
		
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primes[primesMax++]=i;
	}
	
	private static int lessOrEqualPrimeIndex(int target, int endIndex) {
		if (target<primes[0]) return -1;
		else if (target>=primes[endIndex]) return endIndex;
		
		int min=0;
		int max=endIndex;
		while (min<max) {
			int mid=(min+max)/2;
			if (target==primes[mid]) return mid;
			else if (target>primes[mid]) min=mid+1;
			else max=mid;
		}
		
		return min-1;
	}
	
	public static void main (String [] args) throws Exception {
		sieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			int ans=0;
			for (int i=1;i<primesMax && primes[i]<n;i++) ans+=(lessOrEqualPrimeIndex(n-primes[i],i-1)+1);
			
			System.out.printf("Case %d: %d\n", testCase++, ans);
		}
	}

}