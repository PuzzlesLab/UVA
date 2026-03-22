import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static final int MAX_DIST=1000000;
	private static final int MAX_N=1001;
	private static int N;
	private static int K;
	private static Tuple [] ToVisits;
	private static boolean [][] IsToVisit;
	private static int [][] BigDist;
	private static int [][] Dist;
	private static int [][] Deltas={{-1,2},{-2,1},{-1,-2},{-2,-1},{1,2},{2,1},{1,-2},{2,-1}};
	private static int [][] Dp;

	private static void calcBigDist() {
		BigDist=new int [MAX_N][MAX_N];
		LinkedList<Tuple> q=new LinkedList<>();
		q.addLast(new Tuple(0,0));
		while (!q.isEmpty()) {
			Tuple c=q.removeFirst();
			for (int [] d: Deltas) {
				int nx=c.x+d[0];
				int ny=c.y+d[1];
				if (nx>=0 && nx<MAX_N && ny>=0 && ny<MAX_N && BigDist[nx][ny]==0) {
					BigDist[nx][ny]=BigDist[c.x][c.y]+1;
					q.addLast(new Tuple(nx,ny));
				}
			}
		}
		BigDist[0][0]=0;
	}

	private static int getDist(Tuple from, Tuple to) {
		LinkedList<Tuple> q=new LinkedList<>();
		int [][] minDist=new int [N][N];
		for (int n=0;n<N;n++) Arrays.fill(minDist[n],MAX_DIST);

		minDist[from.x][from.y]=0;
		q.addLast(from);
		while (!q.isEmpty()) {
			Tuple c=q.removeFirst();
			if (c.x==to.x && c.y==to.y) return minDist[c.x][c.y];
			for (int [] d: Deltas) {
				int nx=c.x+d[0];
				int ny=c.y+d[1];
				if (nx>=0 && nx<N && ny>=0 && ny<N && minDist[nx][ny]==MAX_DIST) {
					minDist[nx][ny]=minDist[c.x][c.y]+1;
					q.addLast(new Tuple(nx,ny));
				}
			}
		}
		return MAX_DIST;
	}
	
	private static int compute(int curr, int mask) {
		if (mask==(1<<K)-1) return Dist[curr][0];

		if (Dp[curr][mask]==-1) {
			int sol=MAX_DIST;
			for (int i=0;i<K;i++) if ((mask&(1<<i))==0 && Dist[curr][i]<MAX_DIST) {
				sol=Math.min(sol,Dist[curr][i]+compute(i,mask|(1<<i)));
			}
			Dp[curr][mask]=sol;
		}
		return Dp[curr][mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		calcBigDist();
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			ToVisits=new Tuple[K];
			IsToVisit=new boolean [N][N];
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				ToVisits[k]=new Tuple(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
				IsToVisit[ToVisits[k].x][ToVisits[k].y]=true;
			}

			Dist=new int [K][K];
			for (int k=0;k<K;k++) Arrays.fill(Dist[k],MAX_DIST);
			for (int k=0;k<K;k++) {
				Dist[k][k]=0;
				for (int k2=k+1;k2<K;k2++) {
					int dx=Math.abs(ToVisits[k].x-ToVisits[k2].x);
					int dy=Math.abs(ToVisits[k].y-ToVisits[k2].y);
					int d=(dx+dy>20) ? BigDist[dx][dy]:getDist(ToVisits[k],ToVisits[k2]);
					Dist[k][k2]=Dist[k2][k]=Math.min(Dist[k][k2],d);
				}
			}

			Dp=new int [K][1<<K];
			for (int k=0;k<K;k++) Arrays.fill(Dp[k],-1);
			System.out.printf("Case %d: %d\n",tc,compute(0,1));
		}
	}

}