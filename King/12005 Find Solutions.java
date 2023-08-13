import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX=20000000;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [1270607];
	private static int PrimeCount=0;
	
	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int sumDiv(long n) {
		int result=1;
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
			int curr=1;
			while (n%Prime[i]==0) {
				n/=Prime[i];
				curr++;
			}
			result*=curr;
		}
		if (n!=1) result<<=1;
		return result;
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("0")) {
			long C=Long.parseLong(s);
			long ans=sumDiv((C<<2)-3);
			sb.append(C);
			sb.append(' ');
			sb.append(ans);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}
