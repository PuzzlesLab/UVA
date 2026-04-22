import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int x,y,step,xD,yD;
		
		public State(int x, int y, int step, int xD, int yD) {
			this.x=x;
			this.y=y;
			this.step=step;
			this.xD=xD;
			this.yD=yD;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_ANS=10000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			boolean [][] blocked=new boolean [X][Y];

			st=new StringTokenizer(br.readLine());
			int [] S={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			int [] T={Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			int P=Integer.parseInt(br.readLine());
			
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				int y1=Integer.parseInt(st.nextToken());
				int y2=Integer.parseInt(st.nextToken());
				
				for (int x=x1;x<=x2;x++) for (int y=y1;y<=y2;y++) blocked[x][y]=true;
			}
			
			boolean [][][][] visited=new boolean [X][Y][7][7];
			LinkedList<State> q=new LinkedList<>();
			q.addLast(new State(S[0],S[1],0,0,0));
			visited[S[0]][S[1]][3][3]=true;

			int ans=MAX_ANS;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.x==T[0] && curr.y==T[1]) {
					ans=curr.step;
					break;
				}

				// Hop at diff speed.
				for (int dxD=-1;dxD<=1;dxD++) for (int dyD=-1;dyD<=1;dyD++) {
					int nxD=curr.xD+dxD;
					int nyD=curr.yD+dyD;
					if (nxD<-3 || nxD>3 || nyD<-3 || nyD>3) continue;

					int nx=curr.x+nxD;
					int ny=curr.y+nyD;
					// Check the destination is valid.
					if (nx>=0 && nx<X && ny>=0 && ny<Y && !blocked[nx][ny]) {
						if (!visited[nx][ny][nxD+3][nyD+3]) {
							visited[nx][ny][nxD+3][nyD+3]=true;
							q.addLast(new State(nx,ny,curr.step+1,nxD,nyD));
						}
					}
				}
			}
			
			if (ans==MAX_ANS) System.out.println("No solution.");
			else System.out.printf("Optimal solution takes %d hops.\n",ans);
		}
	}

}