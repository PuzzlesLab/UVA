import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Dp;

	private static int compute(int n, int k) {
		if (n<=1) return 0;

		if (Dp[n][k]==-1) {
			int min=Integer.MAX_VALUE;
			for (int i=1;i<=n;i++) {
				int w=n*(k+i);
				min=Math.min(min,w+compute(i-1,k)+compute(n-i,k+i));
			}
			Dp[n][k]=min;
		}
		return Dp[n][k];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [501][501];
		for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			System.out.printf("Case %d: %d\n",tc,compute(N,K));
		}
	}

}
