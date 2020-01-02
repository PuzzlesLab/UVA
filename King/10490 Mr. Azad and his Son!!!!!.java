import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==0) break;
			
			long powTwo=(1 << N) - 1;
			/*
			 * Euclid–Euler theorem:
			 *  - Prefect number = (2^p-1)(2^p - 1), where 2^p - 1 must be a prime number!!
			 */
			if (BigInteger.valueOf(powTwo).isProbablePrime(10)) {
				long ans=powTwo*(1 << (N-1));
				System.out.printf("Perfect: %d!\n", ans);
			}
			else if (BigInteger.valueOf(N).isProbablePrime(10)) System.out.println("Given number is prime. But, NO perfect number is available.");
			else System.out.println("Given number is NOT prime! NO perfect number is available.");
			
		}
	}

}