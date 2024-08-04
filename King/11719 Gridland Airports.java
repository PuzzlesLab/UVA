import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		final BigInteger MOD=new BigInteger("10000000000000007");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			BigInteger all=new BigInteger(st.nextToken()).multiply(new BigInteger(st.nextToken()));
			BigInteger m=all.shiftRight(1);
			BigInteger n=all.subtract(m);

			// https://www.sciencedirect.com/science/article/pii/0012365X9090377T
			BigInteger p1=m.modPow(n.subtract(BigInteger.ONE), MOD);
			BigInteger p2=n.modPow(m.subtract(BigInteger.ONE), MOD);
			System.out.println(p1.multiply(p2).mod(MOD));
		}
 	}

}