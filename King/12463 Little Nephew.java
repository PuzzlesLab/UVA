import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static long [][] Dp;

	private static long nCr(int n, int r) {
		if (r==0 || n==r) return 1;
		if (Dp[n][r]==-1) Dp[n][r]=nCr(n-1,r-1)+nCr(n-1,r);
		return Dp[n][r];
	}

	public static void main(String[] args) throws Exception {
		Dp=new long [21][3];
		for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			long ans=nCr(A,1)*nCr(B,1)*nCr(C,1)*nCr(D,1)*nCr(D,1)*nCr(E,1)*nCr(E,1);
			System.out.println(ans);
		}
	}

}
