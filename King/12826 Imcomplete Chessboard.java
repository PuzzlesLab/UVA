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

	private static final int [][] Deltas={{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int r1=Integer.parseInt(st.nextToken())-1;
			int c1=Integer.parseInt(st.nextToken())-1;
			int r2=Integer.parseInt(st.nextToken())-1;
			int c2=Integer.parseInt(st.nextToken())-1;
			int r3=Integer.parseInt(st.nextToken())-1;
			int c3=Integer.parseInt(st.nextToken())-1;
			
			boolean [][] visited=new boolean [8][8];
			visited[r3][c3]=true;
			
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(r1,c1,0));
			visited[r1][c1]=true;
			int ans=-1;
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.r==r2 && ctx.c==c2) {
					ans=ctx.dist;
					break;
				}
				for (int [] delta: Deltas) {
					int nr=ctx.r+delta[0];
					int nc=ctx.c+delta[1];
					if (nr>=0 && nr<8 && nc>=0 && nc<8 && !visited[nr][nc]) {
						visited[nr][nc]=true;
						q.addLast(new Context(nr,nc,ctx.dist+1));
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb.toString());
		}
	}

}
