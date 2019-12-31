import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger MAX=new BigInteger("1000000000");
		ArrayList<BigInteger> fibs=new ArrayList<>();
		fibs.add(BigInteger.ONE);
		fibs.add(BigInteger.ONE);
		while (true) {
			BigInteger cfib=fibs.get(fibs.size()-1).add(fibs.get(fibs.size()-2));
			fibs.add(cfib);
			if (cfib.compareTo(MAX)>=0) break;
		}
		//Zeckendorf ’s theorem -> Any positive integer can be represented by sum of unconsecutive fib numbers
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			BigInteger q=new BigInteger(br.readLine());
			StringBuilder sb=new StringBuilder();
			for (int i=fibs.size()-1;i>=1;i--) {
				BigInteger b=fibs.get(i);
				if (q.compareTo(b)>=0) {
					q=q.subtract(b);
					sb.append("1");
				} else if (sb.length()>0) sb.append("0");
			}
			System.out.println(sb.toString());
		}
	}

}