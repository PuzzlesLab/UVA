import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int R;
	private static int C;
	private static int [] Dp;

	private static int deflate(int r, int c) {
		return r*C+c;
	}

	private static boolean isBitSet(int board, int r, int c) {
		return (board&(1<<deflate(r,c)))!=0;
	}

	private static int compute(int board) {
		if (Integer.bitCount(board)<=1) return 1;

		if (Dp[board]==-1) {
			int ans=0;
			for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (isBitSet(board,r,c)) {
				// Move 
				for (int dr=-1;dr<=1;dr++) {
					int nr=r+dr;
					if (nr<0 || nr>=R) continue;
					for (int dc=-1;dc<=1;dc++) {
						if (dr==0 && dc==0) continue;
						
						int nc=c+dc;
						if (nc<0 || nc>=C) continue;

						if (isBitSet(board,nr,nc)) { // Move
							int nr2=nr+dr;
							int nc2=nc+dc;
							if (nr2>=0 && nr2<R && nc2>=0 && nc2<C && !isBitSet(board,nr2,nc2)) {
								int nBoard=board;
								nBoard|=1<<deflate(nr2,nc2);
								nBoard^=1<<deflate(nr,nc);
								nBoard^=1<<deflate(r,c);
								ans+=compute(nBoard);
							}
						}
					}
				}
			}
			Dp[board]=ans;
		}
		return Dp[board];
	}

	public static void main(String[] args) throws Exception {
    	Dp=new int [1<<16];
		
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	R=Integer.parseInt(st.nextToken());
        	C=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken());
        	
        	int board=0;
        	for (int n=0;n<N;n++) {
        		st=new StringTokenizer(br.readLine());
        		int r=Integer.parseInt(st.nextToken())-1;
        		int c=Integer.parseInt(st.nextToken())-1;
        		board|=(1<<deflate(r,c));
        	}
        	
        	Arrays.fill(Dp,-1);
        	System.out.printf("Case %d: %d\n",tc,compute(board));
        }
	}

}