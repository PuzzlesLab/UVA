import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX_N=1000001;
	private static boolean [] NotPrime=new boolean [MAX_N];
	private static int [] PrimeNum=new int [78498];
	private static int PrimeNumCount=0;
	private static int [] DivisorCount=new int [MAX_N];
	private static int [] Ans=new int [MAX_N];

	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			PrimeNum[PrimeNumCount++]=i;
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
	}

	private static int countDivisor(int n) {
		if (n==0) return 0;
		if (n==1) return 1;
		int oN=n;
		
		if (DivisorCount[oN]==0) {
			int ans=1;
			for (int i=0;i<PrimeNumCount && PrimeNum[i]<=n;i++) if (n%PrimeNum[i]==0) {
				int temp=1;
				while (n%PrimeNum[i]==0) {
					n/=PrimeNum[i];
					temp++;
				}
				ans*=temp;
			}
			DivisorCount[oN]=ans;
		}
		return DivisorCount[oN];
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		int ansEnd=1;
		Ans[1]=1;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());

			if (N>ansEnd) {
				for (int n=ansEnd;n<=N;n++) Ans[n]=(countDivisor(n)>=countDivisor(Ans[n-1])) ? n : Ans[n-1];
				ansEnd=N;
			}
			sb.append(Ans[N]);
			sb.append('\n');
		}
		System.out.print(sb);
	}

}
