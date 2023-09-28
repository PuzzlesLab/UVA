import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [][] Dp;
	private static int N;

	private static int count(int currN) {
		if (currN>=N) return 0;
		if (currN==N-2 || currN==N-1) return 1;

		if (Dp[currN][N]==-1) Dp[currN][N]=count(currN+2)+count(currN+3);
		return Dp[currN][N];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [77][77];
		for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			N=Integer.parseInt(s);
			System.out.println(count(0)+count(1));
		}
	}

}
