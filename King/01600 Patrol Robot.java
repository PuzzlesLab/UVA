import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int x,y,obsRem,step;
		
		public State(int x, int y, int obsRem, int step) {
			this.x=x;
			this.y=y;
			this.obsRem=obsRem;
			this.step=step;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_ANS=10000000;
		final int [][] deltas={{0,1},{0,-1},{-1,0},{1,0}};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			int K=Integer.parseInt(br.readLine());
			boolean [][] blocked=new boolean [M][N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				for (int n=0;n<N;n++) blocked[m][n]=Integer.parseInt(st.nextToken())==1;
			}

			LinkedList<State> q=new LinkedList<>();
			boolean [][][] visited=new boolean [M][N][K+1];
			q.addLast(new State(0,0,K,0));
			visited[0][0][K]=true;
			
			int ans=MAX_ANS;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.x==M-1 && curr.y==N-1) {
					ans=curr.step;
					break;
				}
				
				for (int [] d: deltas) {
					int nx=curr.x+d[0];
					int ny=curr.y+d[1];
					if (nx>=0 && nx<M && ny>=0 && ny<N) {
						State nS=new State(nx,ny,blocked[nx][ny]?curr.obsRem-1:K,curr.step+1);
						if (nS.obsRem>=0 && !visited[nS.x][nS.y][nS.obsRem]) {
							visited[nS.x][nS.y][nS.obsRem]=true;
							q.addLast(nS);
						}

					}
				}
			}
			
			if (ans==MAX_ANS) ans=-1;
			System.out.println(ans);
		}
	}

}