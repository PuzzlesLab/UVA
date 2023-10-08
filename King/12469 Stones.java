import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int [][] Dp=new int [1001][2002];

	private static boolean play(int n, int max) {
		if (Dp[n][max]==0) {
			for (int i=1;i<=max && i<=n;i++) {
				if (!play(n-i,i<<1)) {
					Dp[n][max]=1;
					return true;
				}
			}
			Dp[n][max]=2;
		}
		return Dp[n][max]==1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			boolean alicia=play(N,N-1);
			System.out.println(alicia?"Alicia":"Roberto");
		}
	}

}
