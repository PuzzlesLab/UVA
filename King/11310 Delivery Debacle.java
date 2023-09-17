import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		long [] dp=new long [41];
		dp[0]=1;
		dp[1]=1;
		dp[2]=5;
		for (int i=3;i<dp.length;i++) {
			// Add L for i-2 (4 possible kinds), Add :: for i-3 (2 possible kinds).
			dp[i]=dp[i-1]+(dp[i-2]<<2)+(dp[i-3]<<1);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
	}

}
