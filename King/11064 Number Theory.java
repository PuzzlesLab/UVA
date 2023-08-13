import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=46341;
	private static boolean [] NotPrime=new boolean [MAX];
	private static long [] Prime=new long [4792];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static long totient(long n) {
		long result=n;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			result-=result/Prime[fi];
			while (n%Prime[fi]==0) n/=Prime[fi];
		}
		if (n!=1) result-=result/n;
		return result;
	}
	
	private static long overlap(long n) {
		long result=1;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			int count=1;
			while (n%Prime[fi]==0) {
				n/=Prime[fi];
				count++;
			}
			result*=count;
		}
		if (n>1) result<<=1;
		return result;
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long N=Long.parseLong(s);
			long c1=totient(N);
			long c2=overlap(N);
			System.out.println(N-c1-c2+1);
		}
	}

}
