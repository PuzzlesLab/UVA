import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_COST=10000000;
	private static final int MAX_GAS=200;
	private static final int INITIAL_GAS=100;
	private static int L;
	private static int [] RefillCostAtLoc;
	private static int [][] Dp;

	private static int find(int currLoc, int gasLeft) {
		if (currLoc==L) return gasLeft>=INITIAL_GAS ? 0 : MAX_COST;
		
		if (Dp[currLoc][gasLeft]==-1) {
			int ans=MAX_COST;
			if (gasLeft>0) ans=Math.min(ans,find(currLoc+1,gasLeft-1));
			if (RefillCostAtLoc[currLoc]!=MAX_COST) {
				for (int gasNext=gasLeft+1;gasNext<=MAX_GAS;gasNext++) {
					ans=Math.min(ans,(gasNext-gasLeft)*RefillCostAtLoc[currLoc]+find(currLoc,gasNext));
				}
			}
			Dp[currLoc][gasLeft]=ans;
		}
		return Dp[currLoc][gasLeft];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			L=Integer.parseInt(br.readLine());

			RefillCostAtLoc=new int[L+1];
			Arrays.fill(RefillCostAtLoc,MAX_COST);
			while (true) {
				String s=br.readLine();
				if (s==null) break;
				s=s.trim();
				if (s.isEmpty()) break;

				StringTokenizer st=new StringTokenizer(s);
				int loc=Integer.parseInt(st.nextToken());
				int cost=Integer.parseInt(st.nextToken());
				if (loc>L) continue;
				RefillCostAtLoc[loc]=Math.min(RefillCostAtLoc[loc],cost); //Select the cheapest.
			}

			Dp=new int [L+1][MAX_GAS+1];
			for (int l=0;l<Dp.length;l++) Arrays.fill(Dp[l],-1);

			if (testCase>0) System.out.println();
			int ans=find(0,INITIAL_GAS);
			System.out.println(ans==MAX_COST ? "Impossible" : ans);
		}
	}

}
