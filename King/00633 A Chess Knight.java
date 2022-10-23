import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y, moves;
		char type;
		
		public Context(int x, int y, int moves, char type) {
			this.x=x;
			this.y=y;
			this.moves=moves;
			this.type=type;
		}
	}

	public static void main(String[] args) throws Exception {
		final int [][] kDeltas={
			{-2,-1}, {-2,1}, {-1,-2}, {-1,2},
			{1,-2}, {1,2}, {2,-1}, {2,1},
		};
		final int [][] bDeltas= {
			{-2,-2}, {-2,2}, {2,-2}, {2,2}
		};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			boolean [][][] visited=new boolean [2*N][2*N][3];

			StringTokenizer st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken())-1;
			int sy=Integer.parseInt(st.nextToken())-1;
			for (int m=0;m<3;m++) visited[sx][sy][m]=true;
			
			st=new StringTokenizer(br.readLine());
			int ex=Integer.parseInt(st.nextToken())-1;
			int ey=Integer.parseInt(st.nextToken())-1;
			
			while (true) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				if (x==0 && y==0) break;
				for (int m=0;m<3;m++) visited[x-1][y-1][m]=true;
			}

			int ans=-1;
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(sx,sy,0,'Z'));
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.x==ex && ctx.y==ey) {
					ans=ctx.moves;
					break;
				}
				
				int nMoves=ctx.moves+1;
				if (ctx.type!='K') {
					for (int [] delta: kDeltas) {
						int nx=ctx.x+delta[0];
						int ny=ctx.y+delta[1];
						if (nx>=0 && nx<visited.length && ny>=0 && ny<visited.length && !visited[nx][ny][0]) {
							visited[nx][ny][0]=true;
							q.addLast(new Context(nx,ny,nMoves,'K'));
						}
					}
				}
				if (ctx.type!='B') {
					for (int [] delta: bDeltas) {
						int nx=ctx.x+delta[0];
						int ny=ctx.y+delta[1];
						if (nx>=0 && nx<visited.length && ny>=0 && ny<visited.length && !visited[nx][ny][1]) {
							visited[nx][ny][1]=true;
							q.addLast(new Context(nx,ny,nMoves,'B'));
						}
					}
				}
				if (ctx.type!='T') {
					int nx=visited.length-1-ctx.x;
					int ny=visited.length-1-ctx.y;
					if (!visited[nx][ctx.y][2]) {
						visited[nx][ctx.y][2]=true;
						q.addLast(new Context(nx,ctx.y,nMoves,'T'));
					}
					if (!visited[ctx.x][ny][2]) {
						visited[ctx.x][ny][2]=true;
						q.addLast(new Context(ctx.x,ny,nMoves,'T'));
					}
				}
			}

			if (ans==-1) System.out.println("Solution doesn't exist");
			else System.out.printf("Result : %d\n", ans);
		}
	}

}
