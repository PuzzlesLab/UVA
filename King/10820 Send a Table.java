import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int MAX=50001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [5133];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int totient(int n) {
		int result=n;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			result/=Prime[fi];
			result*=Prime[fi]-1;
			while (n%Prime[fi]==0) n/=Prime[fi];
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
			
			int count=0;
			for (int x=1;x<=N;x++) count+=totient(x);
			count=(count<<1)-1;
			System.out.println(count);
		}
	}

}
