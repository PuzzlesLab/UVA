import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_N=13;

	public static void main(String[] args) throws Exception {
		int [][][] dp=new int [MAX_N+1][MAX_N+1][MAX_N+1];
		dp[1][1][1]=1;
		for (int n=2;n<=MAX_N;n++) {
			for (int p=1;p<=n;p++) {
				for (int r=1;r<=n;r++) {
					/*
					 * Always try to add shortest person.
					 * - Add to front -> p+1
					 * - Add to back -> r+1
					 * - Add to middle -> (Same p, r) * (n-1-1 positions)
					 */
					dp[n][p][r]=dp[n-1][p-1][r]+dp[n-1][p][r-1]+dp[n-1][p][r]*(n-2);
				}
			}
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());

			// Filter out malicious test cases!!
			if (N>=0 && N<=MAX_N && P>=0 && P<=MAX_N && R>=0 && R<=MAX_N) {
				System.out.println(dp[N][P][R]);
			} else {
				System.out.println(0);
			}
		}
	}

}