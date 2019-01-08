import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			BigInteger fn=BigInteger.ONE;
			BigInteger fnMinus1=BigInteger.ONE;
			
			for (int n=3;n<=N;n++) {
				BigInteger temp=fn;
				fn=fn.add(fnMinus1);
				fnMinus1=temp;
			}
			
			System.out.println(fn.toString());
		}
	}

}
