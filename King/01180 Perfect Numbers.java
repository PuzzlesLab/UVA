import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		StringTokenizer st=new StringTokenizer(br.readLine(), ",");
		BigInteger two=new BigInteger("2");
		while (st.hasMoreTokens()) {
			int num=Integer.parseInt(st.nextToken());
			BigInteger biN=BigInteger.valueOf(num);
			System.out.println((biN.isProbablePrime(11) && two.pow(num).subtract(BigInteger.ONE).isProbablePrime(11)) ? "Yes" : "No");
		}
	}
}