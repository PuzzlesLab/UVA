import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int MAX=10001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [1229];
	private static int PrimeCount=0;

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int numDiv(int n) {
		int ans=1;
		for (int fi=0;fi<PrimeCount && Prime[fi]*Prime[fi]<=n;fi++) if (n%Prime[fi]==0) {
			int p=1;
			while (n%Prime[fi]==0) {
				n/=Prime[fi];
				p++;
			}
			ans*=p;
		}
		return (n!=1)?ans<<1:ans;
	}

	public static void main(String[] args) throws Exception {
		initialize();
		
		int [] ans=new int [10001];
		for (int i=2;i<ans.length;i++) ans[i]=numDiv(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());

			StringBuilder sb=new StringBuilder();
			for (int i=L;i<=H;i++) if (i>=2 && !NotPrime[ans[i]]) {
				sb.append(i);
				sb.append(' ');
			}
			if (sb.length()==0) sb.append(-1);
			else sb.setLength(sb.length()-1);

			System.out.println(sb.toString());
		}
	}

}
