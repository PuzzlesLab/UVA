import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static boolean [][] Dp;
	private static boolean [][] DpExists;
	private static int K;

	private static boolean compute(int pos, int sum) {
		if (pos==Nums.length) return sum==0;

		if (!DpExists[pos][sum+K]) {
			Dp[pos][sum+K]=compute(pos+1,(sum+Nums[pos])%K) || compute(pos+1,(sum-Nums[pos])%K);
			DpExists[pos][sum+K]=true;
		}

		return Dp[pos][sum+K];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			Nums=new int[N];
			for (int i=0;i<Nums.length;i++) Nums[i]=Integer.parseInt(st.nextToken());

			Dp=new boolean[N][K*2+2];
			DpExists=new boolean[N][K*2+2];
			System.out.println(compute(0,0) ? "Divisible" : "Not divisible");
		}
	}

}
