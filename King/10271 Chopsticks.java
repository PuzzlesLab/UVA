import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_BADNESS=1000000;
	private static int N;
	private static int [] Lengths;
	private static int [][] Dp;

	private static int find(int remK, int n) {
		if (remK==0) return 0;
		if (n>=N) return MAX_BADNESS;
		if (N-n<remK*3) return MAX_BADNESS;

		if (Dp[remK][n]==-1) {
			int ans=MAX_BADNESS;
			if (n+1<Lengths.length) {
				int diff=Lengths[n]-Lengths[n+1];
				ans=Math.min(ans,diff*diff+find(remK-1,n+2));
			}
			ans=Math.min(ans,find(remK,n+1));
			Dp[remK][n]=ans;
		}
		return Dp[remK][n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int K=Integer.parseInt(st.nextToken())+8;
			N=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			Lengths=new int [N];
			for (int n=0;n<N;n++) Lengths[n]=Integer.parseInt(st.nextToken());

			Dp=new int [K+1][N];
			for (int k=0;k<=K;k++) Arrays.fill(Dp[k],-1);
			System.out.println(find(K,0));
		}
	}

}
