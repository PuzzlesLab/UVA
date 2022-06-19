import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [] coins=new int [4];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<coins.length;i++) coins[i]=Integer.parseInt(st.nextToken());
			
			// Model as unlimited knapsack.
			BigInteger [] dp=new BigInteger [100001];
			for (int i=0;i<dp.length;i++) dp[i]=BigInteger.ZERO;
			dp[0]=BigInteger.ONE;
			for (int i=0;i<coins.length;i++) for (int i2=coins[i];i2<dp.length;i2++) dp[i2]=dp[i2].add(dp[i2-coins[i]]);

			int Q=Integer.parseInt(st.nextToken());
			for (int q=0;q<Q;q++) {
				int [] limits=new int [4];

				st=new StringTokenizer(br.readLine());
				for (int i=0;i<limits.length;i++) limits[i]=Integer.parseInt(st.nextToken());
				int v=Integer.parseInt(st.nextToken());

				BigInteger ans=BigInteger.ZERO;
				for (int state=0;state<16;state++) {
					int sign=1;
					int temp=v;
					for (int i=0;i<4;i++) {
						boolean coinUsed=((state>>i)&1)!=0;
						if (coinUsed) {
							temp-=(limits[i]+1)*coins[i];
							sign=-sign;
						}
					}
					if (temp>=0) ans=ans.add(dp[temp].multiply(BigInteger.valueOf(sign)));
				}

				System.out.println(ans);
			}
		}
	}

}
