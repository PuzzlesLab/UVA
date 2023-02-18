import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int NULL_VALUE=Integer.MIN_VALUE;
	private static int INVALID_VALUE=Integer.MIN_VALUE>>2;
	private static int S;
	private static int C;
	private static int [][] Profit;
	private static int [][] TravelCost;
	private static int [][] Dp;

	private static int find(int currS, int currC) {
		if (currC==C+1) return 0;
		
		if (Dp[currS][currC]==NULL_VALUE) {
			int ans=INVALID_VALUE;
			for (int nS=1;nS<=S;nS++) {
				int nett=Profit[nS][currC]-TravelCost[currS][nS];
				ans=Math.max(ans,nett+find(nS,currC+1));
			}
			Dp[currS][currC]=ans;
		}
		return Dp[currS][currC];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			S=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			Profit=new int [S+1][C+1];
			for (int s=1;s<=S;s++) {
				st=new StringTokenizer(br.readLine());
				for (int c=1;c<=C;c++) Profit[s][c]=Integer.parseInt(st.nextToken());
			}
			
			TravelCost=new int [S+1][S+1];
			for (int s=1;s<=S;s++) {
				st=new StringTokenizer(br.readLine());
				for (int s2=1;s2<=S;s2++) TravelCost[s][s2]=Integer.parseInt(st.nextToken());
			}

			Dp=new int [S+1][C+1];
			for (int s=0;s<=S;s++) Arrays.fill(Dp[s],NULL_VALUE);
			System.out.println(find(0,0));
		}
	}

}