import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main (String [] args) throws Exception {
		BigInteger [] dp=new BigInteger [10001];
		dp[0]=BigInteger.ZERO;
		int pow=0;
		int i=1;
		while (i<dp.length) {
			BigInteger curr=BigInteger.ONE.shiftLeft(pow);
			for (int i2=0;i2<=pow && i<dp.length;i2++) {
				dp[i]=dp[i-1].add(curr);
				i++;
			}
			pow++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			System.out.println(dp[Integer.parseInt(s)]);
		}
	}

}
