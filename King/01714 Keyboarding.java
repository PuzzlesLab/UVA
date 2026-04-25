import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class State {
		int x,y,toType,step;
		
		public State(int x, int y, int toType, int step) {
			this.x=x;
			this.y=y;
			this.toType=toType;
			this.step=step;
		}
	}

	private static final int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};
	private static int X;
	private static int Y;
	private static char [][] KB;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			X=Integer.parseInt(st.nextToken());
			Y=Integer.parseInt(st.nextToken());
			
			KB=new char [X][Y];
			for (int x=0;x<X;x++) {
				s=br.readLine();
				for (int y=0;y<Y;y++) KB[x][y]=s.charAt(y);
			}

			ArrayList<int []> [][] adjList=new ArrayList [X][Y];
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				adjList[x][y]=new ArrayList<>();
				for (int [] d: Deltas) { // Key skipping doesn't work diagonally. :/
					int tx=x+d[0];
					int ty=y+d[1];
					while (tx>=0 && tx<X && ty>=0 && ty<Y && KB[x][y]==KB[tx][ty]) {
						tx=tx+d[0];
						ty=ty+d[1];
					}
					if (tx>=0 && tx<X && ty>=0 && ty<Y) adjList[x][y].add(new int [] {tx,ty});
				}
			}

			String end=br.readLine()+"*";
			int [][] progress=new int [X][Y];
			for (int x=0;x<X;x++) Arrays.fill(progress[x],-1);

			LinkedList<State> q=new LinkedList<>();
			q.addLast(new State(0,0,0,0));
			int ans=-1;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.toType==end.length()) {
					ans=curr.step;
					break;
				}

				if (KB[curr.x][curr.y]==end.charAt(curr.toType)) {
					State nS=new State(curr.x,curr.y,curr.toType+1,curr.step+1);
					progress[nS.x][nS.y]=Math.max(progress[nS.x][nS.y],nS.toType);
					q.add(nS);
				}

				for (int i=0;i<adjList[curr.x][curr.y].size();i++) {
					int [] n=adjList[curr.x][curr.y].get(i);
					if (progress[n[0]][n[1]]<curr.toType) {
						progress[n[0]][n[1]]=curr.toType;
						q.add(new State(n[0],n[1],curr.toType,curr.step+1));
					}
				}
			} 

			System.out.println(ans);
		}
	}

}