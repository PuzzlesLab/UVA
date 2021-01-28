import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {
	
	private static TreeSet<Long> sieveofEratothenes(int max) {
		TreeSet<Long> primeNumbers=new TreeSet<>();
		primeNumbers.add(0L);
		HashSet<Long> notPrime=new HashSet<>();
		for (long i=2;i<=max;i++) if (!notPrime.contains(i)) {
			primeNumbers.add(i);
			for (long i2=i*i;i2<max;i2+=i) notPrime.add(i2);
		}
		return primeNumbers;
	}
	
	public static void main (String [] args) throws Exception {
		TreeSet<Long> primeNumbers = sieveofEratothenes(1299709);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			long l=Long.parseLong(s);
			if (primeNumbers.contains(l)) System.out.println(0);
			else {
				long lower=primeNumbers.floor(l);
				long upper=primeNumbers.ceiling(l);
				System.out.println(upper-lower);
			}
		}
	}
}