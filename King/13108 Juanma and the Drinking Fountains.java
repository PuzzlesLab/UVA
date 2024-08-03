import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static long [][] Dp;

	public static long nCr(int n, int k) {
		if (n<0) return 0;
		if (k==0 || n==k) return 1;
		if (Dp[n][k]==-1) Dp[n][k]=nCr(n-1,k)+nCr(n-1,k-1);
		return Dp[n][k];
	}

	public static void main(String [] args) throws Exception {
		Dp=new long [201][201];
		for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			// https://en.wikipedia.org/wiki/Dividing_a_circle_into_areas
			System.out.println(nCr(N,4)+nCr(N,2)+1);
		}
 	}

}
