import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Context implements Comparable<Context> {
		int x, y, time;
		
		public Context(int x, int y, int time) {
			this.x=x;
			this.y=y;
			this.time=time;
		}
		
		public int compareTo(Context ctx) {
			return this.time-ctx.time;
		}
	}

	public static void main(String[] args) throws Exception {
		int [][] deltas={{0,1},{-1,0},{0,-1},{1,0}};
		char [] directions={'E','N','W','S'};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			char [][] map=new char [M][];
			ArrayList<Tuple> doors=new ArrayList<>();
			for (int m=0;m<M;m++) {
				map[m]=br.readLine().toCharArray();
				for (int n=0;n<N;n++) if (map[m][n]!='#' && map[m][n]!='.') doors.add(new Tuple(m,n));
			}
			
			int [][] doorCost=new int [M][N];
			if (doors.size()>0) {
				st=new StringTokenizer(br.readLine());
				for (int d=0;d<doors.size();d++) {
					Tuple door=doors.get(d);
					doorCost[door.x][door.y]=Integer.parseInt(st.nextToken());
				}
			}

			int ans=-1;
			int [][] best=new int [M][N];
			for (int m=0;m<M;m++) Arrays.fill(best[m],Integer.MAX_VALUE);
			PriorityQueue<Context> q=new PriorityQueue<>();
			q.offer(new Context(M-1,0,0));
			best[M-1][0]=0;
			while (!q.isEmpty()) {
				Context ctx=q.poll();
				if (ctx.x==0 && ctx.y==N-1) {
					ans=ctx.time;
					break;
				}
				for (int d=0;d<deltas.length;d++) {
					int nx=ctx.x+deltas[d][0];
					int ny=ctx.y+deltas[d][1];
					if (nx<0 || nx>=M || ny<0 || ny>=N) continue; // Out of bound
					if (map[nx][ny]=='#') continue; // Don't walk to snake
					if (map[nx][ny]!='.') {
						char expectedDir=directions[(d+2)%directions.length];
						if (map[nx][ny]!=expectedDir) continue; // Blocked by door
					}

					int nt=ctx.time+1;
					if (map[ctx.x][ctx.y]!='.') {
						if (map[ctx.x][ctx.y]==directions[d]) continue; // No need to go back
						
						int D=doorCost[ctx.x][ctx.y];
						if (map[ctx.x][ctx.y]=='W' && directions[d]=='E') nt=nt+D+D;
						else if (map[ctx.x][ctx.y]=='E' && directions[d]=='W') nt=nt+D+D;
						else if (map[ctx.x][ctx.y]=='N' && directions[d]=='S') nt=nt+D+D;
						else if (map[ctx.x][ctx.y]=='S' && directions[d]=='N') nt=nt+D+D;
						else nt+=D;
					}
					
					if (nt>=best[nx][ny]) continue; // Slower
					best[nx][ny]=nt;
					q.offer(new Context(nx,ny,nt));
				}
			}
			System.out.println(ans==-1?"Poor Kianoosh":ans);
		}
	}

}