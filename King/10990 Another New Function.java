import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=2000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [148933];
	private static int PrimeCount=0;
	private static int [] TotientDp=new int [MAX];
	private static int [] DepthDp=new int [MAX];

	private static void sieve() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int totient(int n) {
		if (n==1) return 1;

		int temp=n;
		if (TotientDp[temp]==0) {
			int ans=n;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) if (n%Prime[i]==0) {
				ans-=ans/Prime[i];
				while (n%Prime[i]==0) n/=Prime[i];
			}
			if (n!=1) ans-=ans/n;
			TotientDp[temp]=ans;
		}
		return TotientDp[temp];
	}

	private static int depth(int n) {
		if (DepthDp[n]==0) {
			int count=0;
			int curr=n;
			while (true) {
				int next=totient(curr);
				if (curr==next) break;
				curr=next;
				count++;
			}
			DepthDp[n]=count;
		}
		return DepthDp[n];
	}

	public static void main(String[] args) throws Exception {
		sieve();
		long [] ansDp=new long [MAX];
		for (int i=2;i<ansDp.length;i++) ansDp[i]=ansDp[i-1]+depth(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int m=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());
			System.out.println(ansDp[n]-ansDp[m-1]);
		}
	}

}