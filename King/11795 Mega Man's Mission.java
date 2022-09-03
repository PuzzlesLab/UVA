import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static long [] Dp;
	
	private static long find (int curr, int visitedCount, int visited, boolean [][] canVisit) {
		if (visitedCount==canVisit.length) return 1;
		else {
			if (Dp[visited]==-1) {
				long sum=0;
				for (int i=1;i<canVisit.length;i++) if ((visited&(1<<i))==0) {
					boolean flag=false;
					for (int i2=0;i2<canVisit.length;i2++) if ((visited&(1<<i2))!=0 && canVisit[i2][i]) {
						flag=true;
						break;
					}
					
					if (flag) sum+=find(i,visitedCount+1,visited|(1<<i),canVisit);
				}
				Dp[visited]=sum;
			}

			return Dp[visited];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			boolean [][] canVisit=new boolean [N+1][N+1];
			for (int n=0;n<=N;n++) {
				char [] curr=br.readLine().toCharArray();
				for (int n2=0;n2<N;n2++) canVisit[n][n2+1]=curr[n2]=='1';
			}

			Dp=new long[(int)Math.pow(2, N+1)];
			Arrays.fill(Dp,-1);
			long count=find(0,1,1,canVisit);
			System.out.printf("Case %d: %d\n", testCase, count);
		}
	}

}
