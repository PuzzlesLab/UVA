import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int MAX=1000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [78498];
	private static int PrimeCount=0;
	private static int [] MuDp=new int [MAX];
	private static int [] MDp=new int [MAX];
	
	private static void sieve() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int mu(int n) {
		if (n==1) return 1;

		if (MuDp[n]==-2) {
			int temp=n;
			int count=0;
			boolean sqFree=true;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=temp;i++) if (temp%Prime[i]==0) {
				int dup=0;
				while (temp%Prime[i]==0) {
					temp/=Prime[i];
					dup++;
				}
				count++;
				sqFree&=dup==1;
			}
			if (temp>1) count++;

			if (!sqFree) MuDp[n]=0;
			else MuDp[n]=count%2==0 ? 1 : -1;
		}
		return MuDp[n];
	}

	private static void calcMDp() {
		MDp[1]=1;
		for (int i=2;i<MAX;i++) MDp[i]=MDp[i-1]+mu(i);
	}

	public static void main(String[] args) throws Exception {
		sieve();
		Arrays.fill(MuDp,-2);
		calcMDp();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			System.out.printf("%8d%8d%8d\n",n,mu(n),MDp[n]);
		}
	}

}