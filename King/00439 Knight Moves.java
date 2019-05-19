import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static int [][] deltas = {{-2,-1},{-2,1},{2,-1},{2,1},				
									  {-1,-2},{-1,2},{1,-2},{1,2}};
	
	private static class Data {
		public int x, y, move;
		public Data (int x, int y, int move) { this.x=x; this.y=y; this.move=move; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			String coor1=st.nextToken();
			String coor2=st.nextToken();
			int x1=coor1.charAt(1)-'1';
			int y1=coor1.charAt(0)-'a';
			int x2=coor2.charAt(1)-'1';
			int y2=coor2.charAt(0)-'a';

			int ans=-1;
			boolean [][] visited=new boolean[8][8];
			visited[x1][y1]=true;
			LinkedList<Data> queue=new LinkedList<>();
			queue.add(new Data(x1,y1,0));
			while (!queue.isEmpty()) {
				Data d=queue.removeFirst();
				if (d.x==x2 && d.y==y2) {
					ans=d.move;
					break;
				}
				for (int [] delta :deltas) {
					int x=d.x+delta[0];
					int y=d.y+delta[1];
					if (x>=0 && x<8 && y>=0 && y<8 && !visited[x][y]) {
						visited[x][y]=true;
						queue.addLast(new Data(x,y,d.move+1));
					}
				}
			}
			
			System.out.printf("To get from %s to %s takes %d knight moves.\n", coor1, coor2, ans);
		}
	}

}