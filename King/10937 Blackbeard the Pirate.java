import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class Context extends Tuple {
		int step;
		
		public Context(int x, int y, int step) {
			super(x, y);
			this.step=step;
		}
	}

	private static final int MAX_DIST=1000000;
	private static int H;
	private static int W;
	private static ArrayList<Tuple> Points;
	private static int [][] Deltas={{0,1},{0,-1},{-1,0},{1,0}};
	private static int P;
	private static int [][] TspDP;

	private static int getDist(char [][] map, Tuple from, Tuple to) {
		LinkedList<Context> q=new LinkedList<>();
		q.addLast(new Context(from.x,from.y,0));
		boolean [][] visited=new boolean[H][W];
		visited[from.x][from.y]=true;

		while (!q.isEmpty()) {
			Context ctx=q.removeFirst();
			if (ctx.x==to.x && ctx.y==to.y) return ctx.step;
			
			for (int [] d: Deltas) {
				int nx=ctx.x+d[0];
				int ny=ctx.y+d[1];
				if (nx>=0 && nx<H && ny>=0 && ny<W && (map[nx][ny]=='@' || map[nx][ny]=='.' || map[nx][ny]=='!') && !visited[nx][ny]) {
					visited[nx][ny]=true;
					q.addLast(new Context(nx,ny,ctx.step+1));
				}
			}
		}
		return MAX_DIST;
	}
	
	private static int tsp(int [][] dist, int curr, int mask) {
		if (mask==(1<<P)-1) return dist[curr][0];
		if (TspDP[curr][mask]==-1) {
			int d=MAX_DIST;
			for (int p=0;p<P;p++) if ((mask&(1<<p))==0) {
				d=Math.min(d,dist[curr][p]+tsp(dist,p,mask|(1<<p)));
			}
			TspDP[curr][mask]=d;
		}
		return TspDP[curr][mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			H=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			
			char [][] map=new char [H][];
			for (int h=0;h<H;h++) map[h]=br.readLine().toCharArray();

			Tuple bb=new Tuple(-1,-1);

			Points=new ArrayList<>();
			Points.add(bb);
			for (int h=0;h<H;h++) for (int w=0;w<W;w++) {
				if (map[h][w]=='@') {
					bb.x=h;
					bb.y=w;
				}
				else if (map[h][w]=='!') Points.add(new Tuple(h,w));
			}
			P=Points.size();

			for (int h=0;h<H;h++) for (int w=0;w<W;w++) if (map[h][w]=='*') {
				// We mark surrounding map of hostile to palm tree.
				for (int i=-1;i<=1;i++) for (int i2=-1;i2<=1;i2++) {
					int nx=h+i;
					int ny=w+i2;
					if (nx>=0 && nx<H && ny>=0 && ny<W && map[nx][ny]!='*') map[nx][ny]='#';
				}
			}

			// Check if any point is blocked.
			boolean noSol=false;
			for (int p=0;p<P;p++) {
				Tuple point=Points.get(p);
				if (map[point.x][point.y]=='#') {
					noSol=true;
					break;
				}
			}
			if (noSol) {
				System.out.println(-1);
				continue;
			}


			int [][] dist=new int [P][P];
			for (int p=0;p<P;p++) {
				Arrays.fill(dist[p],MAX_DIST);
				dist[p][p]=0;
			}
			for (int p=0;p<P;p++) for (int p2=0;p2<P;p2++) dist[p][p2]=dist[p2][p]=getDist(map,Points.get(p),Points.get(p2));

			TspDP=new int [P][1<<P];
			for (int p=0;p<P;p++) Arrays.fill(TspDP[p],-1);
			int ans=tsp(dist,0,1);
			if (ans==MAX_DIST) ans=-1;
			System.out.println(ans);
		}
	}

}