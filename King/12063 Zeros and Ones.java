import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int NHalf;
	private static int K;
	private static long [][][] Dp;

	private static long compute(int zeroes, int ones, int rem) {
		if (zeroes==NHalf && ones==NHalf) return rem==0?1:0;

		if (Dp[zeroes][ones][rem]==-1) {
			long ans=0;
			if (ones>0 && zeroes+1<=NHalf) ans+=compute(zeroes+1,ones,(rem<<1)%K); // Must not put zero as leading digit(s).
			if (ones+1<=NHalf) ans+=compute(zeroes,ones+1,((rem<<1)+1)%K);
			Dp[zeroes][ones][rem]=ans;
		}
		return Dp[zeroes][ones][rem];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	N=Integer.parseInt(st.nextToken());
        	NHalf=N>>1;
        	K=Integer.parseInt(st.nextToken());

        	if (K==0 || (N&1)==1) {
                System.out.printf("Case %d: %s\n",tc,0);
                continue;
        	}

            Dp=new long [NHalf+1][NHalf+1][K];
            for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp.length;i2++) Arrays.fill(Dp[i][i2],-1);
            System.out.printf("Case %d: %s\n",tc,compute(0,0,0));
        }
	}

}