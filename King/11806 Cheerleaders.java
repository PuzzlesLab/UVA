import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MOD=1000007;
	private static final int SIDE_TOP=0;
	private static final int SIDE_RIGHT=1;
	private static final int SIDE_BTM=2;
	private static final int SIDE_LEFT=3;
	private static final int END_MASK=(1<<4)-1;

	private static int M;
	private static int N;
	private static int K;

	private static int [][][][] Dp;

	private static int compute(int k, int m, int n, int mask) {
		if (k==K) return (mask==END_MASK)?1:0;
		if (n==N) {
			m++;
			n=0;
		}
		if (m==M) return 0;

		if (Dp[k][m][n][mask]==-1) {
			int ans=compute(k,m,n+1,mask)%MOD;
			int nMask=mask;
			if (m==0) nMask|=1<<SIDE_TOP;
			if (m==M-1) nMask|=1<<SIDE_BTM;
			if (n==0) nMask|=1<<SIDE_LEFT;
			if (n==N-1) nMask|=1<<SIDE_RIGHT;

			ans=(ans+compute(k+1,m,n+1,nMask))%MOD;
			Dp[k][m][n][mask]=ans;
		}

		return Dp[k][m][n][mask];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	M=Integer.parseInt(st.nextToken());
        	N=Integer.parseInt(st.nextToken());
        	K=Integer.parseInt(st.nextToken());

        	Dp=new int [K+1][M][N][END_MASK+1];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) for (int i3=0;i3<Dp[i][i2].length;i3++)
        		Arrays.fill(Dp[i][i2][i3],-1);
        	System.out.printf("Case %d: %d\n",tc,compute(0,0,0,0));
        }
	}

}