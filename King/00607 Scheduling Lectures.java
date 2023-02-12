import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_DI=Integer.MAX_VALUE/2;
	private static int N;
	private static int L;
	private static int C;
	private static int LCount;
	private static int [] Topics;
	private static int [][] Dp;

	private static int find(int l, int n) {
		if (n==N) return 0;
		if (l==LCount) return MAX_DI;

		if (Dp[l][n]==Integer.MIN_VALUE) {
			int ans=MAX_DI;
			int currLength=0;
			int tempN=n;
			while (tempN<N) {
				currLength+=Topics[tempN];
				if (currLength>L) break;

				int timeLeft=L-currLength;
				int di=0;
				if (timeLeft>=1 && timeLeft<=10) di=-C;
				else if (timeLeft!=0) di=(timeLeft-10)*(timeLeft-10);

				ans=Math.min(ans,di+find(l+1,++tempN));
			}
			Dp[l][n]=ans;
		}

		return Dp[l][n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			L=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			Topics=new int [N];
			for (int n=0;n<N;n++) Topics[n]=Integer.parseInt(br.readLine());

			// Perform greedy to get the min lectures.
			LCount=0;
			int currLength=10000000;
			for (int n=0;n<N;n++) {
				if (currLength+Topics[n]<=L) currLength+=Topics[n];
				else {
					LCount++;
					currLength=Topics[n];
				}
			}

			// Perform Dp with min lectures limit (Note : there exists lower Di with more lectures)
			Dp=new int [LCount][N];
			for (int n=0;n<LCount;n++) Arrays.fill(Dp[n],Integer.MIN_VALUE);
			int ansDi=find(0,0);

			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(":\nMinimum number of lectures: ");
			sb.append(LCount);
			sb.append("\nTotal dissatisfaction index: ");
			sb.append(ansDi);
			System.out.println(sb.toString());
		}
	}

}
