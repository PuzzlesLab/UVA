import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static int [][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
	private static class Data {
		int x, y, count;
		public Data(int x, int y, int c) {
			this.x=x;
			this.y=y;
			this.count=c;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String place=st.nextToken();
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			
			int [][] map=new int [row][col];
			for (int r=0;r<row;r++) {
				st=new StringTokenizer(br.readLine());
				for (int c=0;c<col;c++) map[r][c]=Integer.parseInt(st.nextToken());
			}
			
			int maxLength=-1;
			for (int r=0;r<row;r++) for (int c=0;c<col;c++) {
				int [][] lengths=new int [row][col];
				lengths[r][c]=1;
				
				LinkedList<Data> q=new LinkedList<>();
				q.add(new Data(r,c,1));
				while (!q.isEmpty()) {
					Data dat=q.removeFirst();
					for (int [] delta : deltas) {
						int x=dat.x+delta[0], y=dat.y+delta[1];
						if (x>=0 && x<row && y>=0 && y<col && map[dat.x][dat.y]>map[x][y] && dat.count>=lengths[x][y]) {
							lengths[x][y]=dat.count+1;
							maxLength=Math.max(maxLength, lengths[x][y]);
							q.add(new Data(x,y,lengths[x][y]));
						}
					}
				}
			}
			
			System.out.printf("%s: %d\n", place, maxLength);
		}
	}

}