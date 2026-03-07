import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final long MAX_NUM=1000000000000L;
	private static boolean [] NotPrime=new boolean [1000001];
	private static long [] Primes=new long [78498];
	private static int PrimeCount=0;
	private static long [] AlmostPrime=new long [80070];
	private static int AlmostPrimeCount=0; 
	
	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Primes[PrimeCount++]=i;
	}

	private static int getGEIndex(long n) {
		int idx=Arrays.binarySearch(AlmostPrime,n);
		if (idx<0) return -(idx+1);
		return idx;
	}

	private static int getLEIndex(long n) {
		int idx=Arrays.binarySearch(AlmostPrime,n);
		if (idx<0) idx=-(idx+1)-1;
		return idx;
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		for (int i=0;i<PrimeCount;i++) {
			long curr=Primes[i];
			while (true) {
				curr*=Primes[i];
				if (curr>=MAX_NUM) break;
				AlmostPrime[AlmostPrimeCount++]=curr;
			}
		}
		Arrays.sort(AlmostPrime);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long min=Long.parseLong(st.nextToken());
			long max=Long.parseLong(st.nextToken());

			System.out.println(getLEIndex(max)-getGEIndex(min)+1);
		}
	}

}