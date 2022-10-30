import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Context implements Comparable<Context> {
		int x, y, door;
		
		public Context(int x, int y, int door) {
			this.x=x;
			this.y=y;
			this.door=door;
		}
		
		public int compareTo(Context c) {
			return this.door-c.door;
		}
	}

	private static final int MAX_AXIS=403;
	private static final int WALL=100;
	private static final int DOOR=200;
	
	public static void main(String[] args) throws Exception {
		/*
		 * Simplified problem statement :
		 * There is a large area with a smaller maze area with wall / doors / empty.
		 * Given a starting point, find the path with minimum door that can go out from this maze area.
		 */
		final int [][] deltas={{0,1},{0,-1},{-1,0},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1 -1")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			int [][] map=new int[MAX_AXIS][MAX_AXIS];

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())*2;
				int y=Integer.parseInt(st.nextToken())*2;
				int d=Integer.parseInt(st.nextToken());
				int t=Integer.parseInt(st.nextToken())*2;
				if (d==0) for (int i=1;i<t;i+=2) map[x+i][y]=WALL;
				else for (int i=1;i<t;i+=2) map[x][y+i]=WALL;
			}

			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())*2;
				int y=Integer.parseInt(st.nextToken())*2;
				int d=Integer.parseInt(st.nextToken());
				if (d==0) map[x+1][y]=DOOR;
				else map[x][y+1]=DOOR;
			}

			st=new StringTokenizer(br.readLine());
			int tx=(int)(Math.floor(Double.parseDouble(st.nextToken()))*2+1);
			int ty=(int)(Math.floor(Double.parseDouble(st.nextToken()))*2+1);

			if (tx>=MAX_AXIS || ty>=MAX_AXIS) {
				System.out.println(0);
				continue;
			}
			int ans=Integer.MAX_VALUE;
			int [][] best=new int [MAX_AXIS][MAX_AXIS];
			for (int x=0;x<best.length;x++) Arrays.fill(best[x],Integer.MAX_VALUE);

			PriorityQueue<Context> q=new PriorityQueue<>();
			best[tx][ty]=0;
			q.offer(new Context(tx,ty,0));
			while (!q.isEmpty()) {
				Context ctx=q.poll();
				for (int [] delta: deltas) {
					int nx=ctx.x+delta[0];
					int ny=ctx.y+delta[1];
					if (nx<0 || nx>=MAX_AXIS || ny<0 || ny>=MAX_AXIS) { // Out of walled area
						ans=Math.min(ans,ctx.door);
						continue;
					}
					if (map[nx][ny]==WALL) continue;
					int nDoor=ctx.door+(map[nx][ny]==DOOR?1:0);

					int nx2=nx+delta[0];
					int ny2=ny+delta[1];
					if (nx2<0 || nx2>=MAX_AXIS || ny2<0 || ny2>=MAX_AXIS) { // Out of walled area
						ans=Math.min(ans,nDoor);
						continue;
					}
					if (best[nx2][ny2]<=nDoor) continue;

					best[nx2][ny2]=nDoor;
					q.offer(new Context(nx2,ny2,nDoor));
				}
			}
			System.out.println(ans==Integer.MAX_VALUE?-1:ans);
		}
	}

}
