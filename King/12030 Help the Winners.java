import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int [][] Score;
	private static long [][] Dp;
	
	private static long compute(int sMask, int state) {
		/* State:
		 *   Bit 0 = Has Zero
		 *   Bit 1 = Has Two
		 */
		int dI=Integer.bitCount(sMask);
                // Has two score, proceed. Otherwise we need all to be 1.
		if (dI==N) return ((state&2)!=0 || (state&1)==0) ? 1: 0;

		if (Dp[sMask][state]==-1) {
			long ans=0L;
			for (int i=0;i<N;i++) if ((sMask&(1<<i))==0) {
				int nextState=state;
				if (Score[dI][i]==0) nextState|=1;  // We don't cut the branch here as we could be get super-match later.
				else if (Score[dI][i]==2) nextState|=2;

				ans+=compute(sMask|1<<i,nextState);
			}
			Dp[sMask][state]=ans;
		}
		return Dp[sMask][state];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	N=Integer.parseInt(br.readLine());
        	
        	Score=new int [N][N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		for (int n2=0;n2<N;n2++) Score[n][n2]=Integer.parseInt(st.nextToken());
        	}

        	Dp=new long [1<<N][4];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        	System.out.printf("Case %d: %s\n",tc,compute(0,0));
        }
	}

}