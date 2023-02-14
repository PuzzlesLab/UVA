import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

class Main {

	private static final int NO_VALUE=Integer.MAX_VALUE>>1;
	private static final int MOD=20437;
	private static final int [][] Deltas={{-1,0},{1,0},{0,-1},{0,1}};
	private static char [][] Map;

	private static class Solution {
		int step, count;
		
		public Solution() {
			this.step=Integer.MAX_VALUE;
			this.count=0;
		}
	}

	private static class Context {
		int x, y, step;
		
		public Context(int x, int y, int step) {
			this.x=x;
			this.y=y;
			this.step=step;
		}
	}

	private static Solution bfs(int x, int y, char target) {
		Solution sol=new Solution();
		int maxStep=Map.length*Map.length;

		LinkedList<Context> q=new LinkedList<>();
		q.addLast(new Context(x,y,0));
		int [][] dp=new int [Map.length][Map.length];
		for (int n=0;n<Map.length;n++) Arrays.fill(dp[n],Integer.MAX_VALUE);
		dp[x][y]=0;

		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			if (ctx.step>sol.step || ctx.step>maxStep) break;

			if (Map[ctx.x][ctx.y]==target) {
				sol.step=ctx.step;
				sol.count=(sol.count+1)%MOD;
			}

			for (int d=0;d<Deltas.length;d++) {
				int nx=ctx.x+Deltas[d][0];
				int ny=ctx.y+Deltas[d][1];
				if (nx<0 || nx>=Map.length || ny<0 || ny>=Map.length || Map[nx][ny]=='#') continue;
				if (Map[nx][ny]!='.' && Map[nx][ny]>target) continue;
				if (ctx.step+1>dp[nx][ny]) continue;

				dp[nx][ny]=ctx.step+1;
				q.addLast(new Context(nx,ny,ctx.step+1));
			}
		}

		return sol;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Map=new char [N][];
			int lastFood=0;
			for (int n=0;n<N;n++) {
				Map[n]=br.readLine().toCharArray();
				for (int n2=0;n2<N;n2++) if (Character.isAlphabetic(Map[n][n2]) && (Map[n][n2]-'A')>lastFood) lastFood=Map[n][n2]-'A';
			}

			int [][] foodLoc=new int [26][2];
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (Character.isAlphabetic(Map[n][n2])) {
				foodLoc[Map[n][n2]-'A'][0]=n;
				foodLoc[Map[n][n2]-'A'][1]=n2;
			}

			int ans=0;
			int count=1;
			for (int food=0;food<lastFood;food++) {
				Solution sol=bfs(foodLoc[food][0],foodLoc[food][1],(char)('A'+food+1));
				if (sol.step==Integer.MAX_VALUE) {
					ans=NO_VALUE;
					break;
				} else {
					ans+=sol.step;
					count=(count*sol.count)%MOD;
				}
			}

			if (ans==NO_VALUE) {
				System.out.printf("Case %d: Impossible\n",testCase++);
				continue;
			}

			System.out.printf("Case %d: %d %d\n",testCase++,ans,count);
		}

	}

}
