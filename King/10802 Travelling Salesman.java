import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NO_PROFIT=-100000;
	private static int C;
	private static int [][] Profit;
	private static boolean [] IsEnd;
	private static int [][] Dp;

	private static int find(int curr, int remT) {
		if (remT==0) return IsEnd[curr] ? 0 : NO_PROFIT;

		if (Dp[curr][remT]==-1) {
			int ans=NO_PROFIT;
			for (int next=0;next<C;next++) if (next!=curr) {
				ans=Math.max(ans,Profit[curr][next]+find(next,remT-1));
			}
			Dp[curr][remT]=ans;
		}
		return Dp[curr][remT];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			C=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken())-1;
			int E=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());

			Profit=new int [C][C];
			for (int i=0;i<C;i++) {
				st=new StringTokenizer(br.readLine());
				for (int j=0;j<C;j++) Profit[i][j]=Integer.parseInt(st.nextToken());
			}
			
			IsEnd=new boolean[C];
			st=new StringTokenizer(br.readLine());
			for (int e=0;e<E;e++) IsEnd[Integer.parseInt(st.nextToken())-1]=true;

			Dp=new int [C][T+1];
			for (int c=0;c<C;c++) Arrays.fill(Dp[c],-1);
			System.out.println(Math.max(0,find(S,T)));

			br.readLine(); // Empty
		}
	}

}
