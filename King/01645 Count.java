import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final long MOD=1000000000+7;
	private static long [] Dp=new long [1001];

	private static long compute(int n) {
		if (n<=1) return 1;
		if (Dp[n]==0) {
			long ans=0;
			for (int i=1;i<n;i++) {
				if ((n-1)%i==0) ans=(ans+compute(i))%MOD;
			}
			Dp[n]=ans;
		}
		return Dp[n];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			long ans=compute(N);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
 	}

}