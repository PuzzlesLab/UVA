import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int K;
	private static BigInteger [][][][] Dp;

	private static BigInteger count(int remN, int consH, boolean meet) {
		if (remN==0) return meet ? BigInteger.ONE : BigInteger.ZERO;

		int flagI=meet?1:0;
		if (Dp[remN][consH][K][flagI]==null) {
			BigInteger sum=BigInteger.ZERO;
			sum=sum.add(count(remN-1,0,meet));
			sum=sum.add(count(remN-1,consH+1,meet || (consH+1)>=K));
			Dp[remN][consH][K][flagI]=sum;
		}
		return Dp[remN][consH][K][flagI];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		Dp=new BigInteger [101][101][101][2];

		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			System.out.println(count(N,0,K==0));
		}
	}

}
