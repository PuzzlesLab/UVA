import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MOD=1000000007;
	private static int N;
	private static int M;
	private static int [][][] Dp;

	private static int count(int mask, int last, int len) {
		if (len>M) return 0;

		if (Dp[mask][last][len]==-1) {
			int ans=0;
			if (mask==(1<<N)-1) ans++;
			if (len==0) for (int n=1;n<N;n++) ans=(ans+count(mask|1<<n,n,len+1))%MOD;
			else {
				int n=0;
				if (last-1>=0) {
					n=last-1;
					ans=(ans+count(mask|1<<n,n,len+1))%MOD;
				}
				if (last+1<N) {
					n=last+1;
					ans=(ans+count(mask|1<<n,n,len+1))%MOD;
				}
			}
			Dp[mask][last][len]=ans%MOD;
		}
		return Dp[mask][last][len];
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	N=Integer.parseInt(st.nextToken());
        	M=Integer.parseInt(st.nextToken());

        	Dp=new int [1<<N][N][M+1];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);
        	System.out.println(count(0,0,0));
        }
	}

}