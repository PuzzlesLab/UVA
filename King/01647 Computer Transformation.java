import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N<2) System.out.println("0");
			else {
				BigInteger value=BigInteger.ONE;
				for (int n=4;n<=N;n++) {
					value=value.shiftLeft(1);
					if (n%2==0) value=value.add(BigInteger.ONE);
					else value=value.subtract(BigInteger.ONE);
				}
				System.out.println(value);
			}
		}
	}
}
