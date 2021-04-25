import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			char [] binary=new BigInteger(s).subtract(BigInteger.ONE).toString(2).toCharArray();
			char [] reverse=new char[binary.length];
			for (int i=0;i<binary.length;i++) reverse[i]=binary[binary.length-1-i];

			StringBuilder sb=new StringBuilder("{ ");
			for (int i=0;i<reverse.length;i++) if (reverse[i]=='1') {
				BigInteger powThree=BigInteger.valueOf(3).pow(i);
				sb.append(powThree);
				sb.append(", ");
			}
			if (sb.length()>2) {
				sb.setLength(sb.length()-2);
				sb.append(' ');
			}
			sb.append("}");
			System.out.println(sb.toString());
		}
	}
}