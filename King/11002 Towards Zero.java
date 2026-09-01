import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_V=3001;
	private static int N;
	private static int [][] Board;
	private static int [][][] Dp;

	private static int find(int x, int y, int sum) {
		if (x==0) return Math.abs(sum);

		if (Dp[x][y][sum]==-1) {
			int ans=10000000;

			if (x>=N) {
				ans=Math.min(ans,find(x-1,y,Math.abs(sum+Board[x-1][y])));
				ans=Math.min(ans,find(x-1,y,Math.abs(sum-Board[x-1][y])));
				ans=Math.min(ans,find(x-1,y+1,Math.abs(sum+Board[x-1][y+1])));
				ans=Math.min(ans,find(x-1,y+1,Math.abs(sum-Board[x-1][y+1])));
			} else {
				if (y>0) {
					ans=Math.min(ans,find(x-1,y-1,Math.abs(sum+Board[x-1][y-1])));
					ans=Math.min(ans,find(x-1,y-1,Math.abs(sum-Board[x-1][y-1])));
				}
				if (y<x) {
					ans=Math.min(ans,find(x-1,y,Math.abs(sum+Board[x-1][y])));
					ans=Math.min(ans,find(x-1,y,Math.abs(sum-Board[x-1][y])));
				}
			}
			Dp[x][y][sum]=ans;
		}

		return Dp[x][y][sum];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			Board=new int [2*N-1][N];
			for (int n=0;n<Board.length;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N-Math.abs(N-n-1);n2++) Board[n][n2]=Integer.parseInt(st.nextToken());
			}

			Dp=new int [Board.length][N][MAX_V];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);
			System.out.println(find(Board.length-1,0,Math.abs(Board[Board.length-1][0])));
		}

	}

}