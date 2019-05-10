import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	private static class Coordinate {
		public int x, y;
		public Coordinate(int x, int y) {this.x=x; this.y=y;}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int [][] deltas= {{0,-1},{0,1},{-1,0},{1,0}};
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			st.nextToken();
			int x=Integer.parseInt(st.nextToken());
			
			char [][] map=new char [x][];
			for (int i=0;i<x;i++) map[i]=br.readLine().trim().toCharArray();
			
			int px=-1, py=-1;
			for (int i=0;i<x && px==-1;i++) for (int i2=0;i2<map[i].length && px==-1;i2++) if (map[i][i2]=='P') {
				px=i;
				py=i2;
			}
			
			boolean [][] visited=new boolean[x][map[0].length];
			Stack<Coordinate> stk=new Stack<>();
			stk.push(new Coordinate(px, py));
			visited[px][py]=true;
			
			int ans=0;
			while (!stk.isEmpty()) {
				Coordinate c=stk.pop();
				if (map[c.x][c.y]=='G') ans++;
				if (map[c.x][c.y]!='#') {
					boolean hasTrap=false;
					for (int [] delta : deltas) {
						int nx=c.x+delta[0];
						int ny=c.y+delta[1];
						hasTrap |= (nx>=0 && nx<x && ny>=0 && ny<map[0].length && map[nx][ny]=='T');
					}
					if (!hasTrap) {
						for (int [] delta : deltas) {
							int nx=c.x+delta[0];
							int ny=c.y+delta[1];
							if (nx>=0 && nx<x && ny>=0 && ny<map[0].length && !visited[nx][ny]) {
								visited[nx][ny]=true;
								stk.push(new Coordinate(nx,ny));
							}
						}
					}
				}
			}
			
			System.out.println(ans);
		}
	}

}
