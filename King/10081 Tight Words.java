import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main { 

	private static int K;
	private static double [][] Dp;

	private static double compute(int n, int lastK) {
		if (n==0) return 1.0;

		if (Dp[n][lastK]<0) {
			double sum=compute(n-1,lastK);
			if (lastK-1>=0) sum+=compute(n-1,lastK-1);
			if (lastK+1<=K) sum+=compute(n-1,lastK+1);
			Dp[n][lastK]=sum;
		}
		
		return Dp[n][lastK];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			K=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			Dp=new double[N+1][K+2];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-123);
			double ans=0;
			for (int k=0;k<=K;k++) ans+=compute(N-1,k);
			System.out.printf("%.5f\n",(ans*100.0)/Math.pow(K+1,N));
		}
	}

}