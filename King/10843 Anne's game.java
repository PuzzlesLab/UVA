import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		long [] dp=new long [101];
		final long MOD=2000000011L;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			
			if (dp[N]==0) {
				long base=1;
				for (int n=0;n<N-2;n++) base=(base*N)%MOD;
				dp[N]=base;
			}

			System.out.printf("Case #%d: %d\n",t,dp[N]);
		}
 	}

}
