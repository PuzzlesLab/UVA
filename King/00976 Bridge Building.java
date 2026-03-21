import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_DIST=1000000;
	private static int R;
	private static int C;
	private static int B;
	private static int S;
	private static char [][] Map;
	private static int [][] Deltas={{0,1},{1,0},{-1,0},{0,-1}};
	private static int [] BankDist;
	private static int [][] Dp;

	private static void floodFill(int x, int y, char c) {
		Map[x][y]=c;
		for (int [] d: Deltas) {
			int nx=x+d[0];
			int ny=y+d[1];
			if (nx>=0 && nx<R && ny>=0 && ny<C && Map[nx][ny]=='#') floodFill(nx,ny,c);
		}
	}

	private static int compute(int c, int b) {
		if (b==B) return 0;
		if (b>B || c>=C) return MAX_DIST;

		if (Dp[c][b]==-1) Dp[c][b]=Math.min(compute(c+S+1,b+1)+BankDist[c],compute(c+1,b));
		return Dp[c][b];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			B=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());

			Map=new char [R][C];
			for (int r=0;r<R;r++) Map[r]=br.readLine().toCharArray();

			floodFill(0,0,'N');
			floodFill(R-1,0,'S');

			BankDist=new int [C];
			for (int c=0;c<C;c++) {
				int nEnd=0;
				for (int r=0;r<R;r++)  {
					if (Map[r][c]=='N') nEnd=r;
					else if (Map[r][c]=='S') {
						BankDist[c]=r-nEnd-1;
						break;
					}
				}
			}

			Dp=new int [C][B];
			for (int c=0;c<C;c++) Arrays.fill(Dp[c],-1);
			System.out.println(compute(0,0));
		}
	}

}
