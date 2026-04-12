import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][] Deltas= {{0,-1},{0,1},{-1,0},{1,0}};
	private static boolean [][] Init;
	private static int [] Parity;
	private static int N;
	private static int ANS_MAX;
	private static int Ans;
	
	private static void toggle(int x, int y) {
		for (int [] d: Deltas) {
			int dx=x+d[0];
			int dy=y+d[1];
			if (dx>=0 && dx<N && dy>=0 && dy<N) Parity[dx]^=1<<dy;
		}
	}

	private static void dfs(int x, int y, int depth) {
		if (depth>=Ans) return;
		if (y==N) {
			x++;
			y=0;
			if (x==N) {
				for (int i=0;i<N;i++) if (Parity[i]!=0) return;
				Ans=Math.min(Ans,depth);
				return;
			}
		}

		if (x==0) {
			dfs(x,y+1,depth);
			if (!Init[x][y]) { // 0 -> 1
				toggle(x,y);
				dfs(x,y+1,depth+1);
				toggle(x,y);
			}
		} else {
			if ((Parity[x-1]&(1<<y))!=0) { // Parity from side is odd.
				if (!Init[x][y]) { // 0 -> 1
					toggle(x,y);
					dfs(x,y+1,depth+1);
					toggle(x,y);
				}
			} else dfs(x,y+1,depth);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			N=Integer.parseInt(br.readLine());
			
			Init=new boolean [N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) Init[n][n2]=Integer.parseInt(st.nextToken())==1;
			}
			
			Parity=new int [N];
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) for (int [] d: Deltas) {
				int dx=n+d[0];
				int dy=n2+d[1];
				if (dx>=0 && dx<N && dy>=0 && dy<N && Init[dx][dy]) Parity[n]^=1<<n2;
			}

			ANS_MAX=N*N+1;
			Ans=ANS_MAX;
			dfs(0,0,0);
			if (Ans==ANS_MAX) Ans=-1;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(Ans);
			System.out.println(sb);
		}
	}

}