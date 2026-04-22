import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int r,c,step,moves;
		
		public State(int r, int c, int step, int moves) {
			this.r=r;
			this.c=c;
			this.step=step;
			this.moves=moves;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_ANS=10000000;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());

			int [] s=null;
			int [] e=null;
			boolean [][] blocked=new boolean [R][C];
			for (int r=0;r<R;r++) {
				char [] map=br.readLine().toCharArray();
				for (int c=0;c<C;c++) {
					if (map[c]=='S') s=new int [] {r,c};
					else if (map[c]=='E') e=new int [] {r,c};
					else if (map[c]=='#') blocked[r][c]=true;
				}
			}
			
			LinkedList<State> q=new LinkedList<>();
			boolean [][][] visited=new boolean [R][C][3];
			q.addLast(new State(s[0],s[1],0,0));
			visited[s[0]][s[1]][0]=true;
			
			int ans=MAX_ANS;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.r==e[0] && curr.c==e[1]) {
					ans=curr.moves;
					break;
				}
				int nStep=curr.step+1;
				if (nStep==4) nStep=1;

				// up
				int count=0;
				for (int x=1;x<=nStep;x++) {
					int nx=curr.r-x;
					if (nx>=0 && nx<R && !blocked[nx][curr.c]) count++;
					else break;
				}
				if (count==nStep && !visited[curr.r-nStep][curr.c][nStep-1]) {
					visited[curr.r-nStep][curr.c][nStep-1]=true;
					q.addLast(new State(curr.r-nStep,curr.c,nStep,curr.moves+1));
				}

				// down
				count=0;
				for (int x=1;x<=nStep;x++) {
					int nx=curr.r+x;
					if (nx>=0 && nx<R && !blocked[nx][curr.c]) count++;
					else break;
				}
				if (count==nStep && !visited[curr.r+nStep][curr.c][nStep-1]) {
					visited[curr.r+nStep][curr.c][nStep-1]=true;
					q.addLast(new State(curr.r+nStep,curr.c,nStep,curr.moves+1));
				}
				
				// left
				count=0;
				for (int y=1;y<=nStep;y++) {
					int ny=curr.c-y;
					if (ny>=0 && ny<C && !blocked[curr.r][ny]) count++;
					else break;
				}
				if (count==nStep && !visited[curr.r][curr.c-nStep][nStep-1]) {
					visited[curr.r][curr.c-nStep][nStep-1]=true;
					q.addLast(new State(curr.r,curr.c-nStep,nStep,curr.moves+1));
				}
				
				// right
				count=0;
				for (int y=1;y<=nStep;y++) {
					int ny=curr.c+y;
					if (ny>=0 && ny<C && !blocked[curr.r][ny]) count++;
					else break;
				}
				if (count==nStep && !visited[curr.r][curr.c+nStep][nStep-1]) {
					visited[curr.r][curr.c+nStep][nStep-1]=true;
					q.addLast(new State(curr.r,curr.c+nStep,nStep,curr.moves+1));
				}
			}
			System.out.println(ans==MAX_ANS?"NO":ans);
		}
	}

}