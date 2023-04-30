import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long abModM(long a, long b, long m) {
		return ((a%m)*(b%m))%m;
	}

	private static long modPow(long base, long exp, long mod) {
		long ans=1;
		while (exp>0) {
			if (exp%2==1) ans=abModM(ans,base,mod);
			base=abModM(base,base,mod);
			exp>>=1;
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int MOD=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] A=new int [N];
			long sum=0;
			for (int n=0;n<N;n++) {
				A[n]=Integer.parseInt(st.nextToken());
				sum=(sum+A[n])%MOD;
			}

			long totalNums=modPow(N,K-1,MOD);
			totalNums=abModM(totalNums,K,MOD);

			long ans=abModM(totalNums,sum,MOD);
			System.out.printf("Case %d: %d\n",t,ans);
		}
	}
}
