import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int M;
	private static double Md;
	private static int N;
	private static double [][] Dp;
	
	private static double calc(int remN, int remM) {
		if (remN==0 || remM==0) return 0.0;
		if (remN==1 && remM==1) return 1.0;

		if (Dp[remN][remM]==-1024) {
			double p1=calc(remN-1,remM)*(remM/Md); // All filled so far
			double p2=calc(remN-1,remM-1)*(M-remM+1.0)/Md; // Fill missing.
			Dp[remN][remM]=p1+p2;
		}
		return Dp[remN][remM];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while (!(s=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			Md=(double)M;

			Dp=new double [N+1][M+1];
			for (int n=0;n<=N;n++) Arrays.fill(Dp[n],-1024);
			System.out.printf("Case %d: %.7f\n",TC++,1-calc(N,M));
		}
	}

}
