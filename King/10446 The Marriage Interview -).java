import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static BigInteger [][] Dp;

	private static BigInteger trib(int n, int b) {
		if (n<=1) return BigInteger.ONE;
		if (Dp[n][b]==null) {
			BigInteger count=BigInteger.ONE;
			for (int i=1;i<=b;i++) count=count.add(trib(n-i,b));
			Dp[n][b]=count;
		}
		return Dp[n][b];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			if (N>60) return;
			int B=Integer.parseInt(st.nextToken());

			Dp=new BigInteger [Math.max(1,N+1)][Math.max(1,B+1)];
			System.out.printf("Case %d: %d\n",testCase++,trib(N,B));
		}
	}

}
