import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String [] args) throws Exception {
		final BigInteger TWO=BigInteger.valueOf(2);
		final BigInteger THREE=BigInteger.valueOf(3);
		final BigInteger BI_24=BigInteger.valueOf(24);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
		        // https://en.wikipedia.org/wiki/Dividing_a_circle_into_areas
			BigInteger N=new BigInteger(br.readLine());
			BigInteger NS1=N.subtract(BigInteger.ONE);
			BigInteger NS2=N.subtract(TWO);
			BigInteger NS3=N.subtract(THREE);
			
			BigInteger p1=N.multiply(NS1).divide(TWO);
			BigInteger p2=N.multiply(NS1).multiply(NS2).multiply(NS3).divide(BI_24);
			System.out.println(p1.add(p2).add(BigInteger.ONE));
		}
 	}

}
