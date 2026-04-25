import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {

	private static class State {
		int ax,ay,bx,by,cx,cy,step;
		
		public State(int ax, int ay, int bx, int by, int cx, int cy, int step) {
			this.ax=ax;
			this.ay=ay;
			this.bx=bx;
			this.by=by;
			this.cx=cx;
			this.cy=cy;
			this.step=step;
		}
		
		public State(State s) {
			this.ax=s.ax;
			this.ay=s.ay;
			this.bx=s.bx;
			this.by=s.by;
			this.cx=s.cx;
			this.cy=s.cy;
			this.step=s.step;
		}
		
		public String toString() {
			return "A="+this.ax+","+this.ay+" | B="+this.bx+","+this.by+" | C="+this.cx+","+this.cy;
		}
	}
	
	private static int N;
	private static char [][] Map;

	private static boolean hasSpace(int x, int y, int [] d, State s) {
		int nx=x+d[0];
		int ny=y+d[1];
		
		boolean [][] robots=new boolean [N][N];
		robots[s.ax][s.ay]=true;
		robots[s.bx][s.by]=true;
		robots[s.cx][s.cy]=true;
		
		while (nx>=0 && nx<N && ny>=0 && ny<N) {
			if (Map[nx][ny]=='#') return false;
			if (!robots[nx][ny]) return true;
			
			nx=nx+d[0];
			ny=ny+d[1];
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {
		final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			N=Integer.parseInt(br.readLine());
			Map=new char [N][];
			for (int n=0;n<N;n++) Map[n]=br.readLine().toCharArray();

			int ax=0,ay=0,bx=0,by=0,cx=0,cy=0;
			boolean [][] exit=new boolean [N][N];
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) {
				if (Map[n][n2]=='A') {
					ax=n;
					ay=n2;
				} else if (Map[n][n2]=='B') {
					bx=n;
					by=n2;
				} else if (Map[n][n2]=='C') {
					cx=n;
					cy=n2;
				} else if (Map[n][n2]=='X') exit[n][n2]=true;
			}

			int ans=-1;

			boolean [][][][][][] visited=new boolean [N][N][N][N][N][N];
			LinkedList<State> q=new LinkedList<>();
			q.addLast(new State(ax,ay,bx,by,cx,cy,0));
			visited[ax][ay][bx][by][cx][cy]=true;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (exit[curr.ax][curr.ay] && exit[curr.bx][curr.by] && exit[curr.cx][curr.cy]) {
					ans=curr.step;
					break;
				}

				for (int [] d: Deltas) {
					State next=new State(curr);
					next.step+=1;
					

					if (hasSpace(next.ax,next.ay,d,curr)) {
						next.ax+=d[0];
						next.ay+=d[1];
					}
					if (hasSpace(next.bx,next.by,d,curr)) {
						next.bx+=d[0];
						next.by+=d[1];
					}
					if (hasSpace(next.cx,next.cy,d,curr)) {
						next.cx+=d[0];
						next.cy+=d[1];
					}

					if (!visited[next.ax][next.ay][next.bx][next.by][next.cx][next.cy]) {
						visited[next.ax][next.ay][next.bx][next.by][next.cx][next.cy]=true;
						q.addLast(next);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans==-1?"trapped":ans);
			System.out.println(sb);
		}
	}

}