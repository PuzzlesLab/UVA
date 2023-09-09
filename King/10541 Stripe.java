import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int [] Code;
	private static BigInteger [][] Dp;

	private static BigInteger count(int remN, int remK) {
		if (remK==-1) return BigInteger.ONE;
		if (remN<=0) return BigInteger.ZERO;

		if (Dp[remN][remK]==null) {
			BigInteger curr=BigInteger.ZERO;
			if (remN>=Code[remK]) curr=curr.add(count(remN-(Code[remK]+1),remK-1));
			curr=curr.add(count(remN-1,remK));
			Dp[remN][remK]=curr;
		}
		return Dp[remN][remK];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			Code=new int [K];
			for (int k=0;k<K;k++) Code[k]=Integer.parseInt(st.nextToken());

			BigInteger ans=null;
			Dp=new BigInteger[N+1][K];
			ans=count(N,K-1);

			System.out.println(ans);
		}
	}

}