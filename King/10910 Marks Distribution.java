import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int P;
	private static boolean [][] Computed;
	private static int [][] Dp;
	
	public static int compute(int n, int remT) {
		if (n==Dp.length) return remT==0 ? 1 : 0;
		if (!Computed[n][remT]) {
			int remMax=remT-(Dp.length-n-1)*P;
			int sum=0;
			for (int i=P;i<=remMax;i++) sum+=compute(n+1,remT-i);
			Dp[n][remT]=sum;
			Computed[n][remT]=true;
		}
		return Dp[n][remT];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			P=Integer.parseInt(st.nextToken());
			
			Computed=new boolean [N][T+1];
			Dp=new int [N][T+1];
			System.out.println(compute(0,T));
		}
	}

}
