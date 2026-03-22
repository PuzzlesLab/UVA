import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long [] Nums;
	private static long [] NumSum;
	private static long [][] Dp;
	private static boolean [][] DpExists;
	
	private static long compute(int l, int r) {
		if (l>r) return 0;
		if (!DpExists[l][r]) {
			long sol=Long.MIN_VALUE>>2;
			for (int i=l;i<=r;i++) sol=Math.max(sol,NumSum[i]-(l-1>=0?NumSum[l-1]:0)-compute(i+1,r));
			for (int i=r;i>=l;i--) sol=Math.max(sol,NumSum[r]-(i-1>=0?NumSum[i-1]:0)-compute(l,i-1));
			Dp[l][r]=sol;
			DpExists[l][r]=true;
		}
		return Dp[l][r];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			Nums=new long [N];
			NumSum=new long [N];
			for (int n=0;n<N;n++) {
				Nums[n]=Integer.parseInt(st.nextToken());
				NumSum[n]=(n>0?NumSum[n-1]:0)+Nums[n];
			}

			Dp=new long [N][N];
			DpExists=new boolean [N][N];
			System.out.println(compute(0,N-1));
		}
	}

}