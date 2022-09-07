import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	private static int M;
	private static BigInteger [][][] Dp;

	private static BigInteger compute(int remN, int remK, long bit, int currM, boolean lastBit) {
		if (remN==0) return (remK==0) ? BigInteger.ONE: BigInteger.ZERO;

		if (Dp[remN][remK][currM]==null) {
			BigInteger ans=BigInteger.ZERO;

			if (currM<M && remN>remK) {
				ans=ans.add(compute(remN-1,remK,(bit<<1)+(lastBit?1:0),currM+1,lastBit));
			}

			if (remK>0) {
				lastBit=!lastBit;
				ans=ans.add(compute(remN-1,remK-1,(bit<<1)+(lastBit?1:0),1,lastBit));
			}
			Dp[remN][remK][currM]=ans;
		}

		return Dp[remN][remK][currM];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			Dp=new BigInteger [N][K][M+1];
			System.out.println(compute(N-1,K-1,1,1,true));
		}
	}

}
