import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static int dfs (boolean [] vis, int [] next, int curr, int [] dp) {
		if (vis[curr]) return 0;
		else if (next[curr]==-1) return 1;
		else {
			vis[curr]=true;
			dp[curr]=1+dfs(vis, next, next[curr], dp);
			return dp[curr];
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount; testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [] next=new int [N];
			Arrays.fill(next, -1);
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				next[src]=dest;
			}

			
			int [] dp=new int [N];
			Arrays.fill(dp, -1);
			for (int n=0;n<N;n++) if (dp[n]==-1) dp[n]=dfs(new boolean [N], next, n, dp);

			int maxDepth=-1;
			int maxDepthN=-1;
			for (int n=0;n<N;n++) if (dp[n]>maxDepth) {
				maxDepth=dp[n];
				maxDepthN=n+1;
			}
			
			System.out.println("Case "+testCase+": "+maxDepthN);
		}
	}

}
