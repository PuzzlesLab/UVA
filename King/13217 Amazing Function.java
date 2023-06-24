import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger TWO=BigInteger.valueOf(2);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			BigInteger N=new BigInteger(s);
			if (N.equals(BigInteger.ZERO)) System.out.println("5.5957541127");
			else if (N.equals(BigInteger.ONE)) System.out.println("1.7320508076");
			else if (N.equals(TWO)) System.out.println("-0.2679491924");
			else {
				boolean isOdd=N.mod(TWO).equals(BigInteger.ONE);
				System.out.println(isOdd ? "-1.4226497308" : "-2.5773502692");
			}
		}
	}

}
