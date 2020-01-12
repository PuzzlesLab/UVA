import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Data {
		int x, y, length;
		public Data (int x, int y, int l) {
			this.x=x;
			this.y=y;
			this.length=l;
		}
	}
	public static void main (String [] args) throws Exception {
		int [][] deltas= {{0,1},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int W=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			boolean [][] blocked=new boolean [W][N];
			for (int w=0;w<W;w++) {
				st=new StringTokenizer(br.readLine());
				int sn=Integer.parseInt(st.nextToken())-1;
				while (st.hasMoreTokens()) blocked[sn][Integer.parseInt(st.nextToken())-1]=true;
			}
			
			int [][] ways=new int [W][N];
			ways[0][0]=1;
			
			int [][] length=new int [W][N];
			for (int w=0;w<W;w++) for (int n=0;n<N;n++) length[w][n]=-1;
			length[0][0]=0;
			
			LinkedList<Data> q=new LinkedList<>();
			q.addFirst(new Data(0,0,0));
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (dat.length == length[dat.x][dat.y]) {
					for (int [] delta : deltas) {
						int x=dat.x+delta[0];
						int y=dat.y+delta[1];
						if (x<W && y<N && !blocked[x][y]) {
							ways[x][y]++;
							length[x][y]=dat.length+1;
							q.addLast(new Data(x,y,length[x][y]));
						};
					}
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(ways[W-1][N-1]);
		}
	}

}