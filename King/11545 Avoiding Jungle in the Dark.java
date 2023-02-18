import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int NULL_VALUE = Integer.MAX_VALUE >> 1;
	private static final int MAX_VALUE=Integer.MAX_VALUE >> 2;
	private static final int MAX_TRAVEL=16;
	private static char [] Map;
	private static int [][][] Dp;

	private static int find(int pos, int time, int travel) {
		if (travel>MAX_TRAVEL) return MAX_VALUE;
		if (pos==Map.length-1) return 0;
		if (Map[pos]=='*' && (time>=12 || time==0)) return MAX_VALUE;

		if (Dp[pos][time][travel]==NULL_VALUE) {
			int ans=MAX_VALUE;
			ans=Math.min(ans,9+find(pos+1,(time+9)%24,1));
			ans=Math.min(ans,1+find(pos+1,(time+1)%24,travel+1));
			Dp[pos][time][travel]=ans;
		}
		return Dp[pos][time][travel];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			Map=br.readLine().toCharArray();

			Dp=new int [Map.length][24][MAX_TRAVEL+1];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],NULL_VALUE);
			int ans=find(0,0,0);
			if (ans==MAX_VALUE) ans=-1;

			System.out.printf("Case #%d: %d\n",testCase,ans);
		}
	}

}
