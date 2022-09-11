import java.util.Arrays;
import java.util.Scanner;

class Main { 

	private static long [][] Dp;
	private static int [] Nums;
	private static int MOD;

	private static long compute(int start, int k) {
		if (k==0) return 1;
		if (start>=Nums.length) return 0;

		if (Dp[start][k]==-1) {
			long sum=0;
			sum=(sum+compute(start+1,k))%MOD; //Include current digit
			sum=(sum+compute(start+1,k-1)*Nums[start])%MOD; // Don't include current digit
			Dp[start][k]=sum;
		}

		return Dp[start][k];
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			int N=sc.nextInt();
			MOD=sc.nextInt();
			if (N==0 && MOD==0) break;
	
			Nums=new int [N];
			for (int n=0;n<N;n++) Nums[n]=sc.nextInt();

			Dp=new long [N+1][N+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);

			long ans=0;
			for (int k=1;k<=N;k++) ans=Math.max(ans,compute(0,k));
			System.out.println(ans);
		}
	}

}
