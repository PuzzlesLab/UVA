import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static class Coordinate {
		public int x, y;
		public Coordinate (int x, int y) { this.x=x; this.y=y; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int [][] deltas= {{0,-1},{0,1},{-1,0},{1,0}};
		
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [][] color=new int [N][N];
			for (int i=1;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					int x=Integer.parseInt(st.nextToken())-1;
					int y=Integer.parseInt(st.nextToken())-1;
					color[x][y]=i;
				}
			}

			int [] colorRegionCount=new int [N];
			int [] colorRegionSize=new int [N];
			boolean [][] visited=new boolean [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (!visited[i][i2]) {
				colorRegionCount[color[i][i2]]++;
				Stack<Coordinate> stk=new Stack<>();
				stk.push(new Coordinate(i,i2));
				visited[i][i2]=true;
				
				while (!stk.isEmpty()) {
					Coordinate c=stk.pop();
					colorRegionSize[color[c.x][c.y]]++;
					for (int [] delta : deltas) {
						int x=c.x+delta[0];
						int y=c.y+delta[1];
						if (x>=0 && x<N && y>=0 && y<N && color[x][y]==color[i][i2] && !visited[x][y]) {
							visited[x][y]=true;
							stk.push(new Coordinate(x,y));
						}
					}
				}
			}
			
			boolean flag=true;
			for (int i=0;i<N;i++) flag&=(colorRegionCount[i]==1 && colorRegionSize[i]==colorRegionSize[0]);
			System.out.println(flag?"good":"wrong");
		}
	}

}