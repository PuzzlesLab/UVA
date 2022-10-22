import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y;
		StringBuilder path;
		
		public Context(int x, int y, StringBuilder sb) {
			this.x=x;
			this.y=y;
			this.path=sb;
		}
	}

	private static final int [][] Deltas={{-1,0},{1,0},{0,-1},{0,1}};
	private static final char [] Directions={'N','S','W','E'};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int sy=Integer.parseInt(st.nextToken());
			int sx=Integer.parseInt(st.nextToken());
			if (sx==0 && sy==0) break;
			
			st=new StringTokenizer(br.readLine());
			int ey=Integer.parseInt(st.nextToken());
			int ex=Integer.parseInt(st.nextToken());
			
			boolean [][][][] wall=new boolean [8][8][8][8];
			for (int i=0;i<3;i++) {
				st=new StringTokenizer(br.readLine());
				int y1=Integer.parseInt(st.nextToken());
				int x1=Integer.parseInt(st.nextToken());
				int y2=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());

				boolean horizontal=x1==x2;
				if (horizontal) {
					x2++;
					y1++;
					int tempy=y1;
					while (tempy<=y2) {
						wall[x1][tempy][x1+1][tempy]=true;
						wall[x1+1][tempy][x1][tempy]=true;
						tempy++;
					}
				} else {
					x1++;
					y2++;
					int tempx=x1;
					while (tempx<=x2) {
						wall[tempx][y1][tempx][y1+1]=true;
						wall[tempx][y1+1][tempx][y1]=true;
						tempx++;
					}
				}
			}
			
			boolean [][] visited=new boolean [8][8];
			LinkedList<Context> q=new LinkedList<>();
			q.addLast(new Context(sx,sy,new StringBuilder()));
			visited[sx][sy]=true;
			String ans=null;
			while (!q.isEmpty()) {
				Context ctx=q.removeFirst();
				if (ctx.x==ex && ctx.y==ey) {
					ans=ctx.path.toString();
					break;
				}
				for (int i=0;i<Deltas.length;i++) {
					int nx=ctx.x+Deltas[i][0];
					int ny=ctx.y+Deltas[i][1];
					if (nx>=1 && nx<7 && ny>=1 && ny<7 && !wall[ctx.x][ctx.y][nx][ny] && !visited[nx][ny]) {
						visited[nx][ny]=true;
						StringBuilder sb=new StringBuilder(ctx.path);
						sb.append(Directions[i]);
						q.addLast(new Context(nx,ny,sb));
					}
				}
			}
			System.out.println(ans);
		}
	}

}
