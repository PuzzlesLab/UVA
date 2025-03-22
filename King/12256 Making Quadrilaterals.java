import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		long [] dp=new long [61];
		dp[1]=1;
		dp[2]=1;
		dp[3]=1;
		for (int i=4;i<dp.length;i++) dp[i]=dp[i-1]+dp[i-2]+dp[i-3];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			int n=Integer.parseInt(br.readLine());
			if (n==0) break;
			System.out.printf("Case %d: %d\n",tc++,dp[n]);
		}
 	}

}
