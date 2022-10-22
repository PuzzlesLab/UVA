import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y, time;
		public Context(int x, int y, int t) {
			this.x=x;
			this.y=y;
			this.time=t;
		}
	}

	public static void main(String[] args) throws Exception {
		final int [][] deltas = {{0,1},{0,-1},{-1,0},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());

			int R=Integer.parseInt(br.readLine());
			boolean [][][][] blocked=new boolean [X][Y][X][Y];
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int y1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				int y2=Integer.parseInt(st.nextToken());
				blocked[x1][y1][x2][y2]=true;
			}

			int M=Integer.parseInt(br.readLine());
			boolean [][][] cctv=new boolean [X][Y][501];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int t=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				cctv[x][y][t]=true;
			}
			
			int ans=-1;
			boolean [][] visited=new boolean [X][Y];
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(0,0,0));
			visited[0][0]=true;
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.x==X-1 && ctx.y==Y-1) {
					ans=ctx.time;
					break;
				}
				int nt=ctx.time+1;

				boolean existsCCTV=false;
				for (int [] delta: deltas) {
					int nx=ctx.x+delta[0];
					int ny=ctx.y+delta[1];
					if (nx>=0 && nx<X && ny>=0 && ny<Y && !visited[nx][ny] && !blocked[ctx.x][ctx.y][nx][ny]) {
						if (!cctv[nx][ny][nt]) {
							visited[nx][ny]=true;
							q.addLast(new Context(nx,ny,nt));
						} else existsCCTV=true;
					}
				}
				if (existsCCTV) q.add(new Context(ctx.x,ctx.y,nt));
			}
			System.out.println(ans);
		}
	}

}