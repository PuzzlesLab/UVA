import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Data {
		int x, y, move;
		public Data(int x, int y, int move) { this.x=x; this.y=y; this.move=move; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		int [][] horseMoves= {{-1,-2},{-1,2},{1,-2},{1,2},{-2,-1},{-2,1},{2,-1},{2,1}};
		int [][] kingMoves= {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken()), Y=Integer.parseInt(st.nextToken());
			char [][] map=new char [X][];
			for (int x=0;x<X;x++) map[x]=br.readLine().toCharArray();
			
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) if (map[x][y]=='Z') for (int [] hm : horseMoves) {
				int nx=x+hm[0], ny=y+hm[1];
				if (nx>=0 && nx<X && ny>=0 && ny<Y && map[nx][ny]=='.') map[nx][ny]='X';
			}
			
			int sx=-1, sy=-1, ex=-1, ey=-1;
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) {
				if (map[x][y]=='A') {
					sx=x;
					sy=y;
				} else if (map[x][y]=='B') {
					ex=x;
					ey=y;
				}
			}
			
			int ans=-1;
			
			boolean [][] visited=new boolean [X][Y];
			LinkedList<Data> queue=new LinkedList<>();
			visited[sx][sy]=true;
			queue.addLast(new Data(sx,sy,0));
			while (!queue.isEmpty()) {
				Data d=queue.removeFirst();
				if (d.x==ex && d.y==ey) {
					ans=d.move;
					break;
				}
				for (int [] km : kingMoves) {
					int nx=d.x+km[0], ny=d.y+km[1];
					if (nx>=0 && nx<X && ny>=0 && ny<Y && map[nx][ny]!='Z' && map[nx][ny]!='X' && !visited[nx][ny]) {
						visited[nx][ny]=true;
						queue.addLast(new Data(nx, ny, d.move+1));
					}
				}
			}
			
			if (ans==-1) System.out.println("King Peter, you can't go now!");
			else System.out.printf("Minimal possible length of a trip is %d\n", ans);
		}
	}

}