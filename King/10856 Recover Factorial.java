import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static boolean [] NotPrime=new boolean [2800001];
	private static int [] PrimeNum=new int [203363];
	private static int PrimeNumCount=0;
	private static int [] FirstPF=new int [NotPrime.length];
	private static int [] PFCount=new int [NotPrime.length];

	private static void eSieve() {
		NotPrime[0]=NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) {
				FirstPF[i2]=i;
				NotPrime[i2]=true;
			}
		}

		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) PrimeNum[PrimeNumCount++]=i;
	}

	private static int calcPFCount(int n) {
		if (n<=1) return 0;
		if (!NotPrime[n]) return 1;
		
		if (PFCount[n]==-1) PFCount[n]=1+calcPFCount(n/FirstPF[n]);
		return PFCount[n];
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		Arrays.fill(PFCount,-1);

		int [] pfCulCount=new int [NotPrime.length];
		for (int i=1;i<NotPrime.length;i++) pfCulCount[i]=pfCulCount[i-1]+calcPFCount(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N<0) break;

			int X=Arrays.binarySearch(pfCulCount,N);
			if (N==0) X=0;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			if (X<0) sb.append("Not possible.");
			else {
				sb.append(X);
				sb.append('!');
			}
			System.out.println(sb.toString());
		}
	}

}