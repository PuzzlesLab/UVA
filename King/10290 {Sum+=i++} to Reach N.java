import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=4782970;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [334349];
	private static int PrimeCount=0;
	
	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	public static void main(String[] args) throws Exception {
		initialize();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long N=Long.parseLong(s);
			if (N==0) {
				System.out.println(0);
				continue;
			}

			while ((N&1)==0) N>>=1;
			int ans=1;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=N;i++) {
				int pow=1;
				while (N%Prime[i]==0) {
					N/=Prime[i];
					pow++;
				}
				ans*=pow;
			}
			if (N>1) ans<<=1;
			System.out.println(ans);
		}
	}

}
