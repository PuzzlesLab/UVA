import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		final BigInteger TWO=new BigInteger("2");
		final BigInteger FIVE=new BigInteger("5");
		final int [] ANS={56,96,36,76,16};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			BigInteger bi=new BigInteger(br.readLine());
			if (bi.equals(BigInteger.ZERO)) System.out.println(1);
			else if (bi.equals(BigInteger.ONE)) System.out.println(66);
			else {
				bi=bi.subtract(TWO).mod(FIVE);
				System.out.println(ANS[bi.intValue()]);
			}
		}
	}
}
