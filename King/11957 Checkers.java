import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [][] deltas= {{1,-1},{1,1}};
		int MOD=1000007;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			char [][] board=new char[N][];
			for (int n=0;n<N;n++) board[n]=br.readLine().toCharArray();
			
			int wx=-1, wy=-1;
			for (int i=0;i<N && wx==-1;i++) for (int i2=0;i2<N && wy==-1;i2++) if (board[i][i2]=='W') {
				wx=i;
				wy=i2;
			}
			
			int [][] ways=new int [N][N];
			for (int col=0;col<N;col++) if (board[0][col]!='B') ways[0][col]=1;
			
			for (int row=0;row<N;row++) for (int col=0;col<N;col++) {
				for (int [] delta : deltas) {
					int dx=row+delta[0];
					int dy=col+delta[1];
					boolean add=false;
					if (dx>=0 && dx<N && dy>=0 && dy<N) {
						if (board[dx][dy]=='B') {
							dx+=delta[0];
							dy+=delta[1];
							add=(dx>=0 && dx<N && dy>=0 && dy<N && board[dx][dy]!='B');
						} else add=true;
					}
					if (add) ways[dx][dy]=(ways[dx][dy]+ways[row][col])%MOD;
				}
			}
			
			System.out.printf("Case %d: %d\n", testCase, ways[wx][wy]);
		}
	}

}