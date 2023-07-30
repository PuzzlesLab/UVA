import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			if (M==0 && W==0) break;

			int C=Integer.parseInt(st.nextToken());
			double mp=(M+0.0)/(M+W);
			double [][] dp=new double [C+1][2];
			dp[0][0]=1.0;
			for (int i=1;i<dp.length;i++) {
				dp[i][0]=dp[i-1][0]*(1-mp)+dp[i-1][1]*mp;
				dp[i][1]=dp[i-1][0]*mp+dp[i-1][1]*(1-mp);
			}
			System.out.printf("%.7f\n", dp[C][0]);
		}
	}

}
