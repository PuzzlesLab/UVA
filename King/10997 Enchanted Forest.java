import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int r, c, dist;
		
		public Context(int r, int c, int dist) {
			this.r=r;
			this.c=c;
			this.dist=dist;
		}
	}

	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			if (R==0 && C==0) break;
			
			boolean [][] blocked=new boolean [R][C];
			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				blocked[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=true;
			}
			
			int N=Integer.parseInt(br.readLine());
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken())-1;
				int c=Integer.parseInt(st.nextToken())-1;
				int L=Integer.parseInt(st.nextToken());

				int maxr=r+L;
				int maxc=c+L;
				for (int nr=r-L;nr<=maxr;nr++) for (int nc=c-L;nc<=maxc;nc++) {
					if (nr>=0 && nr<R && nc>=0 && nc<C) {
						int dr=nr-r;
						int dc=nc-c;
						if (dr*dr+dc*dc<=L*L) blocked[nr][nc]=true;
					}
				}
			}

			boolean [][] visited=new boolean [R][C];
			LinkedList<Context> q=new LinkedList<>();
			q.add(new Context(0,0,0));
			visited[0][0]=true;
			int ans=-1;
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.r==R-1 && ctx.c==C-1) {
					ans=ctx.dist;
					break;
				}
				for (int [] delta: Deltas) {
					int nr=ctx.r+delta[0];
					int nc=ctx.c+delta[1];
					if (nr>=0 && nr<R && nc>=0 && nc<C && !blocked[nr][nc] && !visited[nr][nc]) {
						visited[nr][nc]=true;
						q.addLast(new Context(nr,nc,ctx.dist+1));
					}
				}
			}
			System.out.println(ans==-1?"Impossible.":ans);
		}
	}

}