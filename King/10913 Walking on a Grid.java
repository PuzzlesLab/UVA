import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=Integer.MIN_VALUE;
	private static final int NO_ANS=Integer.MIN_VALUE>>2;
	private static int [][][][] Dp;
	private static int [][] Mat;
	private static int N;
	private static int K;
	private static int [][] Deltas={{1,0},{0,-1},{0,1}};

	private static int find(int x, int y, int k, int d) {
		if (k>K) return NO_ANS;
		if (x==N-1 && y==N-1) return Mat[x][y];

		if (Dp[x][y][k][d]==NULL) {
			int ans=NO_ANS;
			for (int nd=0;nd<Deltas.length;nd++) {
				if (d==2 && nd==1 || d==1 && nd==2) continue; //If came from left, we can't go right to prevent loop. Same to reverse.

				int [] delta=Deltas[nd];
				int nx=x+delta[0];
				int ny=y+delta[1];
				if (nx<0 || nx>=N || ny<0 || ny>=N) continue;
				int nk=k+(Mat[nx][ny]>=0?0:1);

				ans=Math.max(ans,find(nx,ny,nk,nd));
			}
			if (ans!=NO_ANS) ans+=Mat[x][y];
			Dp[x][y][k][d]=ans;
		}
		return Dp[x][y][k][d];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			Mat=new int [N][N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) Mat[n][n2]=Integer.parseInt(st.nextToken());
			}

			Dp=new int [N][N][K+1][Deltas.length];
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) for (int k=0;k<=K;k++) Arrays.fill(Dp[x][y][k],NULL);
			int ans=find(0,0,Mat[0][0]>=0?0:1,0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(": ");
			sb.append(ans==NO_ANS?"impossible":ans);
			System.out.println(sb.toString());
		}
	}

}
