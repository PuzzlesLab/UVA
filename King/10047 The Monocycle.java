import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	// blue, white, green, black, red.
	private static final int ALL_COLORS=5;
	private static final int GREEN=2;
	private static final int [][] Deltas={{-1,0},{0,1},{1,0},{0,-1}};
	private static final int MAX_TIME=1000000;
	private static char [][] Map;
	private static int M;
	private static int N;

	
	private static class State {
		int x,y,cId,dId,time;
		
		public State(int x, int y, int c, int d, int t) {
			this.x=x;
			this.y=y;
			this.cId=c;
			this.dId=d;
			this.time=t;
		}
		
		public String toString() {
			return "X="+this.x+", Y="+this.y+", color="+this.cId+", direction="+this.dId;
		}
	}

	private static int bfs(int [] s, int [] t) {
		LinkedList<State> q=new LinkedList<>();
		q.addLast(new State(s[0],s[1],GREEN,0,0));
		boolean [][][][] visited=new boolean [M][N][ALL_COLORS][Deltas.length];
		visited[s[0]][s[1]][GREEN][0]=true;

		while (!q.isEmpty()) {
			State curr=q.removeFirst();
			if (curr.x==t[0] && curr.y==t[1] && curr.cId==GREEN) return curr.time;

			// Try turn left, right.
			int nD=(curr.dId+1)%Deltas.length;
			if (!visited[curr.x][curr.y][curr.cId][nD]) {
				visited[curr.x][curr.y][curr.cId][nD]=true;
				q.addLast(new State(curr.x,curr.y,curr.cId,nD,curr.time+1));
			}
			nD=(curr.dId-1)%Deltas.length;
			if (nD<0) nD+=Deltas.length;
			if (!visited[curr.x][curr.y][curr.cId][nD]) {
				visited[curr.x][curr.y][curr.cId][nD]=true;
				q.addLast(new State(curr.x,curr.y,curr.cId,nD,curr.time+1));
			}

			// Move with current direction.
			int nx=curr.x+Deltas[curr.dId][0];
			int ny=curr.y+Deltas[curr.dId][1];
			if (nx>=0 && nx<M && ny>=0 && ny<N && Map[nx][ny]!='#') {
				int nC=(curr.cId+1)%ALL_COLORS;
				if (!visited[nx][ny][nC][curr.dId]) {
					visited[nx][ny][nC][curr.dId]=true;
					q.addLast(new State(nx,ny,nC,curr.dId,curr.time+1));
				}
			}
		}
		
		return MAX_TIME;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());

			Map=new char [M][];
			int [] start=null;
			int [] end=null;
			for (int m=0;m<M;m++) {
				Map[m]=br.readLine().toCharArray();
				for (int n=0;n<N;n++) {
					if (Map[m][n]=='S') start=new int []{m,n};
					else if (Map[m][n]=='T') end=new int []{m,n};
				}
			}

			int ans=bfs(start,end);
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Case #");
			sb.append(tc++);
			sb.append('\n');
			if (ans==MAX_TIME) sb.append("destination not reachable");
			else {
				sb.append("minimum time = ");
				sb.append(ans);
				sb.append(" sec");
			}
			System.out.println(sb);
		}
	}

}