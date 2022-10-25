import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int x, y;
		StringBuilder moves;
		
		public Context(int x, int y) {
			this.x=x;
			this.y=y;
			this.moves=new StringBuilder();
		}
	}

	private static class Edge implements Comparable<Edge> {
		int sx, sy, bx, by;
		StringBuilder moves;

		public Edge(int sx, int sy, int bx, int by, StringBuilder sb) {
			this.sx=sx;
			this.sy=sy;
			this.bx=bx;
			this.by=by;
			this.moves=new StringBuilder(sb);
		}

		private int getPushCount() {
			int count=0;
			for (int i=0;i<this.moves.length();i++) if (Character.isUpperCase(this.moves.charAt(i))) count++;
			return count;
		}

		public int compareTo(Edge e) {
			int thisP=this.getPushCount();
			int eP=e.getPushCount();
			if (thisP!=eP) return thisP-eP;
			return this.moves.length()-e.moves.length();
		}
	}

	private static final char [] Directions={'W','N','E','S'};
	private static final int [][] Deltas={{0,-1},{-1,0},{0,1},{1,0}};

	private static Context sp1(char [][] map, int sx, int sy, int bx, int by, int dx, int dy) {
		if (sx==dx && sy==dy) return new Context(dx,dy);

		boolean [][] visited=new boolean [map.length][map[0].length];
		LinkedList<Context> q=new LinkedList<>();
		q.addLast(new Context(sx,sy));
		visited[sx][sy]=true;
		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			if (ctx.x==dx && ctx.y==dy) return ctx;
			for (int d=0;d<Directions.length;d++) {
				int nx=ctx.x+Deltas[d][0];
				int ny=ctx.y+Deltas[d][1];
				if (nx>=0 && nx<map.length && ny>=0 && ny<map[nx].length && !visited[nx][ny] && map[nx][ny]=='.') {
					if (nx==bx && ny==by) continue; // Can't pass through box!
					visited[nx][ny]=true;
					Context nctx=new Context(nx,ny);
					nctx.moves.append(ctx.moves);
					nctx.moves.append(Character.toLowerCase(Directions[d]));
					q.addLast(nctx);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());

			int sx=-1;
			int sy=-1;
			int bx=-1;
			int by=-1;
			int tx=-1;
			int ty=-1;
			char [][] map=new char [R][];
			for (int r=0;r<R;r++) {
				map[r]=br.readLine().toCharArray();
				for (int c=0;c<C;c++) {
					if (map[r][c]=='S') {
						sx=r;
						sy=c;
						map[r][c]='.';
					} else if (map[r][c]=='B') {
						bx=r;
						by=c;
						map[r][c]='.';
					} else if (map[r][c]=='T') {
						tx=r;
						ty=c;
						map[r][c]='.';
					}
				}
			}
			
			PriorityQueue<Edge> q=new PriorityQueue<>();
			int [][][][] dist=new int [R][C][R][C];
			for (int r1=0;r1<R;r1++) for (int c1=0;c1<C;c1++) for (int r2=0;r2<R;r2++) Arrays.fill(dist[r1][c1][r2],100000);
			dist[sx][sy][bx][by]=0;
			q.offer(new Edge(sx,sy,bx,by,new StringBuilder()));

			String ans=null;
			while (!q.isEmpty()) {
				Edge edge=q.poll();
				if (edge.bx==tx && edge.by==ty) {
					ans=edge.moves.toString();
					break;
				}
				for (int d=0;d<Directions.length;d++) {
					int nsx=edge.bx+Deltas[(d+2)%Directions.length][0];
					int nsy=edge.by+Deltas[(d+2)%Directions.length][1];
					if (nsx<0 || nsx>=R || nsy<0 || nsy>=C) continue; // me out of bound
					if (nsx==edge.bx && nsy==edge.by) continue; // can't move me to rock
					if (map[nsx][nsy]=='#') continue; // can't move me to wall

					int nbx=edge.bx+Deltas[d][0];
					int nby=edge.by+Deltas[d][1];					
					if (nbx<0 || nbx>=R || nby<0 || nby>=C) continue; // rock out of bound
					if (map[nbx][nby]=='#') continue; // can't move rock to wall

					Context ctx=sp1(map,edge.sx,edge.sy,edge.bx,edge.by,nsx,nsy);
					if (ctx==null) continue; // can't move to next position to move rock

					StringBuilder moves=new StringBuilder(edge.moves);
					if (moves.length()==0 || ctx.moves.length()>1) moves.append(ctx.moves); // Only record starting move or >1 step moves.
					moves.append(Directions[d]);
					if (moves.length()<dist[edge.bx][edge.by][nbx][nby]) {
						dist[edge.bx][edge.by][nbx][nby]=moves.length();
						Edge nEdge=new Edge(edge.bx,edge.by,nbx,nby,moves);
						q.offer(nEdge);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Maze #");
			sb.append(tc++);
			sb.append('\n');
			sb.append(ans==null?"Impossible.":ans);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
