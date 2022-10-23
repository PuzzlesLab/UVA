import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y, state, moves;
		
		public Context(int x, int y, int state, int moves) {
			this.x=x;
			this.y=y;
			this.state=state;
			this.moves=moves;
		}
	}

	public static void main(String[] args) throws Exception {
		final int [][] kDeltas={
			{-2,-1}, {-2,1}, {-1,-2}, {-1,2},
			{1,-2}, {1,2}, {2,-1}, {2,1},
		};
		final int [][] mkDeltas= {
			{-3,-1}, {-3,1}, {-1,-3}, {-1,3},
			{1,-3}, {1,3}, {3,-1}, {3,1},	
		};
		final int [][] mpDeltas = {
			{0,-1},{0,1},{-1,0},{1,0}
		};
		final int [][] finalDeltas = {
			{-1,-1},{-1,1},{1,-1},{1,1}
		};
		final int INVALID_STATE=999;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			if (N==0) break;
			StringBuilder sb=new StringBuilder();
			sb.append("Set ");
			sb.append(tc++);
			sb.append(":\n");

			int Q=Integer.parseInt(st.nextToken());
			for (int qu=0;qu<Q;qu++) {
				st=new StringTokenizer(br.readLine());
				int B=Integer.parseInt(st.nextToken())-1;
				int E=Integer.parseInt(st.nextToken())-1;
				
				int sx=B/N;
				int sy=B%N;
				int ex=E/N;
				int ey=E%N;
				int ans=-1;

				boolean [][][] visited=new boolean [N][N][3];
				LinkedList<Context> q=new LinkedList<>();
				for (int i=0;i<3;i++) {
					q.addLast(new Context(sx,sy,i,0));
					visited[sx][sy][i]=true;
				}
				while (!q.isEmpty()) {
					Context ctx=q.removeFirst();
					if (ctx.x==ex && ctx.y==ey) {
						ans=ctx.moves;
						break;
					}
					if (ctx.state==INVALID_STATE) continue;

					int nm=ctx.moves+1;
					int ns=(ctx.state+1)%3;
					if (ctx.state==0) { // Knight
						for (int [] delta: kDeltas) {
							int nx=ctx.x+delta[0];
							int ny=ctx.y+delta[1];
							if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny][ns]) {
								visited[nx][ny][ns]=true;
								q.addLast(new Context(nx,ny,ns,nm));
							}
						}
					} else if (ctx.state==1) { // Mutant knight
						for (int [] delta: mkDeltas) {
							int nx=ctx.x+delta[0];
							int ny=ctx.y+delta[1];
							if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny][ns]) {
								visited[nx][ny][ns]=true;
								q.addLast(new Context(nx,ny,ns,nm));
							}
						}
					} else if (ctx.state==2) { // Mutant pawn
						for (int [] delta: mpDeltas) {
							int nx=ctx.x+delta[0];
							int ny=ctx.y+delta[1];
							if (nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny][ns]) {
								visited[nx][ny][ns]=true;
								q.addLast(new Context(nx,ny,ns,nm));
							}
						}
						for (int [] delta: finalDeltas) { // Final step
							int nx=ctx.x+delta[0];
							int ny=ctx.y+delta[1];
							if (nx>=0 && nx<N && ny>=0 && ny<N) {
								q.addLast(new Context(nx,ny,INVALID_STATE,nm));
							}
						}
					}
				}
				sb.append(ans);
				sb.append('\n');
			}

			System.out.print(sb.toString());
		}
	}

}