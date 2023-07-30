import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			double p=Double.parseDouble(st.nextToken());
			if (N==0) break;

			double [] pPow=new double [N+1];
			pPow[0]=1.0;
			for (int n=1;n<pPow.length;n++) pPow[n]=pPow[n-1]*p;

			double [][] dp=new double [N+1][N+1];
			for (int n=0;n<dp.length;n++) dp[0][n]=1;
			for (int i=1;i<=N;i++) {
				for (int i2=0;i2<=N;i2++) {
					dp[i][i2]=dp[i-1][i2];

					if (i-1==i2) dp[i][i2]-=pPow[i];
					else if (i-1>i2) dp[i][i2]-=dp[i-i2-2][i2]*(1-p)*pPow[i2+1];
				}
			}

			double ans=0;
			for (int i=1;i<=N;i++) ans+=i*(dp[N][i]-dp[N][i-1]);
			System.out.printf("%.6f\n",ans);
		}
	}

}
