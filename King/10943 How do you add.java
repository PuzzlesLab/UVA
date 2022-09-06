import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MOD=1000000;
	private static int [][] Dp;

	private static int count(int N, int K) {
		if (N==0) return 1;
		if (K==0) return 0;

		if (Dp[N][K]==-1) {
			int sum=0;
			for (int n=0;n<=N;n++) {
				sum=(sum+count(n,K-1))%MOD;
			}
			Dp[N][K]=sum;
		}
		return Dp[N][K];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			Dp=new int [N+1][K+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			System.out.println(count(N,K));
		}
	}

}
