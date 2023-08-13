import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX=500001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [41538];
	private static int PrimeCount=0;
	private static int [] SOPFDp=new int [MAX];
	private static int [] LSOPFDp=new int [MAX];

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int sopf(int n) {
		if (SOPFDp[n]==0) {
			int count=0;
			int temp=n;
			for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=n;i++) {
				while (temp%Prime[i]==0) {
					temp/=Prime[i];
					count+=Prime[i];
				}
			}
			if (temp!=1) count+=temp;
			SOPFDp[n]=count;
		}
		return SOPFDp[n];
	}

	private static int lsopf(int n) {
		if (LSOPFDp[n]==0) {
			int next=sopf(n);
			LSOPFDp[n]=(n==next) ? 1 : 1+lsopf(next);
		}
		return LSOPFDp[n];
	}

	public static void main(String[] args) throws Exception {
		initialize();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			if (n>m) {
				int temp=n;
				n=m;
				m=temp;
			}
			
			int max=0;
			for (int i=n;i<=m;i++) max=Math.max(max,lsopf(i));

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(":\n");
			sb.append(max);
			System.out.println(sb.toString());
		}
	}

}