import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_STEP=100000;
	private static Point [] Treasures;
	private static int N;
	private static int T;
	private static int S;
	private static Point End;
	private static int Ans;

	private static class Point implements Comparable<Point> {
		int x,y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			return (this.x!=p.x) ? this.x-p.x : this.y-p.y;
		}
		
		public int dist(Point p) {
			return Math.abs(this.x-p.x)+Math.abs(this.y-p.y);
		}
	}

	private static void dfs(int curr, int mask, int rem, int currDist) {
		if (currDist+rem>=Ans) return;
		if (rem==0) {
			Ans=Math.min(Ans,currDist+Treasures[curr].dist(End));
			return;
		}

		for (int t=1;t<Treasures.length;t++) if ((mask&(1<<t))==0) {
			dfs(t,mask|(1<<t),rem-1,currDist+Treasures[curr].dist(Treasures[t]));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			T=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());

			Treasures=new Point [T+1];
			Treasures[0]=new Point(0,0);
			for (int t=1;t<=T;t++) {
				st=new StringTokenizer(br.readLine());
				Treasures[t]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(Treasures,1,Treasures.length); // Sort for heuristic.

			End=new Point(N-1,N-1);
			Ans=MAX_STEP;
			dfs(0,1,S,0);
			System.out.printf("Case %d: %d\n",tc++,Ans);
		}
	}

}