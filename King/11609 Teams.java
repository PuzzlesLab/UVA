import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger MOD = new BigInteger("1000000007");

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			BigInteger N=new BigInteger(br.readLine());
			/*
			 *     N
			 * summation n x NCn
			 *    n=1
			 */
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": ");
			sb.append(N.mod(MOD).multiply(new BigInteger("2").modPow(N.subtract(BigInteger.ONE), MOD)).mod(MOD));
			System.out.println(sb.toString());
		}
	}

}
