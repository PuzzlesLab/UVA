import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=1000000;
	private static boolean [] NotPrime=new boolean [MAX+1];
	private static int [] Prime=new int [78498];
	private static int PrimeCount;
	private static long [] LCM=new long [MAX+1];

	private static void sieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static void calcLCM() {
		int [] pow=new int [Prime[Prime.length-1]+1];
		LCM[1]=1;
		for (int N=2;N<LCM.length;N++) {
			LCM[N]=LCM[N-1];

			int tempN=N;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=tempN;i++) {
				int dup=0;
				while (tempN%Prime[i]==0) {
					tempN/=Prime[i];
					dup++;
				}
				while (pow[Prime[i]]<dup) {
					pow[Prime[i]]++;
					LCM[N]*=Prime[i];
				}
			}
			if (tempN>1 && pow[tempN]==0) {
				pow[tempN]++;
				LCM[N]*=tempN;
			}
			
			while (LCM[N]%10==0) LCM[N]/=10;
			LCM[N]%=(MAX*10);
		}
	}

	public static void main(String[] args) throws Exception {
		sieve();
		calcLCM();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			System.out.println(LCM[Integer.parseInt(s)]%10);
		}
	}

}
