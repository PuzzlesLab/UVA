import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int F;
	private static BigInteger [][][] Dp;

	private static BigInteger count(int remN, int remS) {
		if (remS<0) return BigInteger.ZERO;
		if (remN==0) return remS==0 ? BigInteger.ONE : BigInteger.ZERO;
		
		if (Dp[F][remN][remS]==null) {
			BigInteger sum=BigInteger.ZERO;
			for (int f=1;f<=F;f++) sum=sum.add(count(remN-1,remS-f));
			Dp[F][remN][remS]=sum;
		}
		return Dp[F][remN][remS];
	}

	public static void main(String[] args) throws Exception {
		Dp=new BigInteger[51][51][4001];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			F=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());

			System.out.printf("%s/%s\n",count(N,S),BigInteger.valueOf(F).pow(N));
		}
	}

}
