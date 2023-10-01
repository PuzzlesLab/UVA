import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static final BigInteger [] Dp=new BigInteger[251];
	
	private static BigInteger count(int remN) {
		if (remN<0) return BigInteger.ZERO;
		if (remN<=1) return BigInteger.ONE;
		if (Dp[remN]==null) Dp[remN]=count(remN-1).add(count(remN-2).shiftLeft(1));
		return Dp[remN];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			System.out.println(count(N));
		}
	}

}
