import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=220001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [19618];
	private static int PrimeCount=0;

	private static void sieve() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int totient(int n) {
		if (n==1) return 2;

		int ans=n;
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			ans-=ans/Prime[i];
			while (n%Prime[i]==0) n/=Prime[i];
		}
		if (n!=1) ans-=ans/n;
		return ans;
	}

	private static long gcd(long a, long b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String[] args) throws Exception {
		sieve();
		long [] phiSum=new long [MAX];
		for (int i=1;i<phiSum.length;i++) phiSum[i]=phiSum[i-1]+totient(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long k=Long.parseLong(br.readLine());
			if (k==0) break;

			if (k==2) {
				System.out.println("1/1");
				continue;
			}

			long num=0;
			long den=0;
			for (int i=1;i<phiSum.length;i++) if (phiSum[i]>=k) {
				den=i;
				
				long count=phiSum[i-1];
				for (long c=1;c<den;c++) if (gcd(c,den)==1) {
					count++;
					if (count==k) {
						num=c;
						break;
					}
				}
				break;
			}
			System.out.println(num+"/"+den);
		}
	}

}
