import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			/*
			 * Initial state = close.
			 * Even number multiplier doors = close.
			 * Odd number multiplier doors = open. (Only happens in perfect square)
			 * 
			 * We find the nearest perfect square <=N.
			 */
			BigInteger bi=new BigInteger(s);
			BigInteger low=BigInteger.ONE;
			BigInteger high=bi;

			while (high.compareTo(low.add(BigInteger.ONE))>0) {
				BigInteger mid=low.add(high).shiftRight(1);
				BigInteger midSq=mid.multiply(mid);
				if (midSq.compareTo(bi)<=0) low=mid;
				else high=mid;
			}
			System.out.println(low.multiply(low));
		}
	}

}
