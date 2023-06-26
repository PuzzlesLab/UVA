import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			BigInteger B1=new BigInteger(st.nextToken());
			BigInteger S1=new BigInteger(st.nextToken());

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": :-");
			if (B1.equals(BigInteger.ONE)) {
				sb.append('\\');
			} else {
				BigInteger B2=B1.subtract(BigInteger.ONE);
				BigInteger S2=S1.subtract(BigInteger.ONE);

				BigInteger gcd=B1.gcd(B2);
				B1=B1.divide(gcd);
				B2=B2.divide(gcd);
				
				BigInteger eS1=S1.multiply(B2);
				BigInteger eB1=B1.multiply(B2);
				if (eS1.compareTo(eB1)>=0) eS1=eB1; // S/B <= 1
				BigInteger eS2=S2.multiply(B1);
				BigInteger eB2=B2.multiply(B1);
				if (eS2.compareTo(eB2)>=0) eS2=eB2; // S/B <= 1

				int cmp=eS2.compareTo(eS1);
				if (cmp>0) sb.append(')');
				else if (cmp==0) sb.append('|');
				else sb.append('(');
			}
			System.out.println(sb.toString());
		}
	}

}
