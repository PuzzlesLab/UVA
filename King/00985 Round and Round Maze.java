import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas={{-1,0},{0,1},{1,0},{0,-1}};
	private static final int [] DirIdx=new int [128];

	private static class State {
		int x,y,step;
		
		public State(int x, int y, int s) {
			this.x=x;
			this.y=y;
			this.step=s;
		}
	}

	private static void initialize() {
		DirIdx['N']=0;
		DirIdx['E']=1;
		DirIdx['S']=2;
		DirIdx['W']=3;
	}

	public static void main(String[] args) throws Exception {
		initialize();
 
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> [][] dirs=new ArrayList [X][Y];
			int maxLine=X*Y-1;
			for (int i=0;i<maxLine;i++) {
				int x=i/Y;
				int y=i%Y;
				dirs[x][y]=new ArrayList<>();
				s=br.readLine();
				for (int i2=0;i2<s.length();i2++) dirs[x][y].add(DirIdx[s.charAt(i2)]);
			}
			
			int ans=-1;
			LinkedList<State> q=new LinkedList<>();
			boolean [][][] visited=new boolean [X][Y][Deltas.length];
			q.addLast(new State(0,0,0));
			visited[0][0][0]=true;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.x==X-1 && curr.y==Y-1) {
					ans=curr.step;
					break;
				}

				for (int i=0;i<dirs[curr.x][curr.y].size();i++) {
					int nD=(dirs[curr.x][curr.y].get(i)+curr.step)%Deltas.length;
					int nx=curr.x+Deltas[nD][0];
					int ny=curr.y+Deltas[nD][1];
					State nS=new State(nx,ny,curr.step+1);
					if (nS.x>=0 && nS.x<X && nS.y>=0 && nS.y<Y && !visited[nS.x][nS.y][nS.step%Deltas.length]) {
						visited[nx][ny][nS.step%Deltas.length]=true;
						q.addLast(nS);
					}
				}
			}
			System.out.println(ans==-1?"no path to exit":ans);
		}
	}

}