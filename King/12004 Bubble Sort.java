import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			//Worse swap count = summation of arithmetic progression = [n(n-1)]/2
			//Average swap = worst swap / 2
			BigInteger bi=new BigInteger(br.readLine());
			BigInteger bi2=bi.subtract(BigInteger.ONE);
			
			BigInteger up=bi.multiply(bi2);
			BigInteger btm=new BigInteger("4");
			BigInteger gcd=up.gcd(btm);
			
			up=up.divide(gcd);
			btm=btm.divide(gcd);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t);
			sb.append(": ");
			sb.append(up.toString());
			if (!btm.equals(BigInteger.ONE)) {
				sb.append('/');
				sb.append(btm.toString());
			}
			System.out.println(sb.toString());
		}
	}

}