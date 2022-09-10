import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Dp;

	private static int compute(int n, int terms) {
		if (n==0) return terms;
		
		if (Dp[n]==Integer.MAX_VALUE) {
			for (int i=(int)Math.sqrt(n);i>=1;i--) Dp[n]=Math.min(Dp[n],compute(n-i*i,terms)+1);
		}

		return Dp[n];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [10001];
		Arrays.fill(Dp,Integer.MAX_VALUE);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			// Don't reset Dp, reusable in all test cases.
			System.out.println(compute(N,0));
		}
	}

}
