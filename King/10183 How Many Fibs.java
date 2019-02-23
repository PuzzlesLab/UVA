import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		ArrayList<BigInteger> fibs=new ArrayList<>();
		fibs.add(BigInteger.valueOf(1));
		fibs.add(BigInteger.valueOf(2));
		BigInteger max=BigInteger.valueOf(10).pow(100);
		
		BigInteger fibN2=BigInteger.valueOf(1);
		BigInteger fibN1=BigInteger.valueOf(2);
		while (true) {
			BigInteger fib=fibN1.add(fibN2);
			if (fib.compareTo(max)<=0) fibs.add(fib);
			else break;
			
			fibN2=fibN1;
			fibN1=fib;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			BigInteger small=new BigInteger(st.nextToken());
			BigInteger large=new BigInteger(st.nextToken());
			
			int count=0;
			for (BigInteger fib : fibs) {
				if (fib.compareTo(large)>0) break;
				if (fib.compareTo(small)>=0) count++;
			}
			System.out.println(count);
		}
	}

}
