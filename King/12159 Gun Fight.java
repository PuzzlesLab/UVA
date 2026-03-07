import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int cross(Tuple t) {
			return this.x*t.y-this.y*t.x;
		}

		public Tuple toVec(Tuple t) {
			return new Tuple(t.x-this.x,t.y-this.y);
		}

		public int distSq(Tuple t) {
			int dx=this.x-t.x;
			int dy=this.y-t.y;
			return dx*dx+dy*dy;
		}
	}

	private static class Tower {
		boolean team;
		Tuple pos;
		int pow;
		
		public Tower(int x, int y, int p) {
			this.pos=new Tuple(x, y);
			this.pow=p;
		}

		public static boolean ccw(Tuple p, Tuple q, Tuple r) {
			Tuple pq=p.toVec(q);
			Tuple pr=p.toVec(r);
			return pq.cross(pr)>0;
		}
		
		public long distSq(Tower t) {
			return this.pos.distSq(t.pos);
		}
	}

	private static int [] Pair;
	private static Tower [] Towers;
	private static int N;
	private static long R;
	private static boolean SmallTeam;

	private static boolean mcbm(int curr, boolean [] visited) {
		if (visited[curr]) return false;

		visited[curr]=true;
		for (int n=0;n<N;n++) {
			if (Towers[n].pow==0 || Towers[n].team==SmallTeam) continue;
			if (Towers[curr].pow<=Towers[n].pow) continue;
			if (Towers[curr].distSq(Towers[n])>R) continue;

			if (Pair[n]==-1 || mcbm(Pair[n],visited)) {
				Pair[n]=curr;
				return true;
			}
		}
		return false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			if (N==0) break;
			
			Towers=new Tower[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Towers[n]=new Tower(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			Tower free1=Towers[Integer.parseInt(st.nextToken())-1];
			Tower free2=Towers[Integer.parseInt(st.nextToken())-1];
			R=Integer.parseInt(st.nextToken());
			R=R*R;


			int [] count=new int [2];
			for (int n=0;n<N;n++) if (Towers[n].pow!=0) {
				Towers[n].team=Tower.ccw(free1.pos,free2.pos,Towers[n].pos);
				count[Towers[n].team?1:0]++;
			}
			SmallTeam=count[0]>count[1];

			Pair=new int [N];
			Arrays.fill(Pair,-1);
			int ans=0;
			for (int n=0;n<N;n++) if (Towers[n].pow!=0 && Towers[n].team==SmallTeam) {
				boolean hasMatch=mcbm(n, new boolean[N]);
				if (hasMatch) ans++;
			}

			System.out.printf("Case %d: %d\n",tc++,ans);
		}
	}

}
