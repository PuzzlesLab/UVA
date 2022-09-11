import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main { 

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] prices=new int [N+1];
			for (int n=1;n<=N;n++) prices[n]=Integer.parseInt(st.nextToken());
			
			int [][] dp=new int [N+1][2];
			for (int n=0;n<dp.length;n++) dp[n][1]=Integer.MIN_VALUE;
			
			for (int i=1;i<=N;i++) {
				dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]); // Not holding any due to [Not buying or selling last bought]
				dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]-C); // Holding due to [last holding or buy current]
			}
			
			System.out.println(Math.max(dp[N][0],dp[N][1]));
		}
	}

}
