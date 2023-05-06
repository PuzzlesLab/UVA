import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static int toInt(char c) {
		if (c>='0' && c<='9') return c-'0';
		if (c>='A' && c<='Z') return c-'A'+10;
		return c-'a'+36;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			s=s.trim();
			if (s.charAt(0)=='+' || s.charAt(0)=='-') s=s.substring(1);
			int [] digits=new int [s.length()];
			for (int i=0;i<s.length();i++) digits[i]=toInt(s.charAt(i));
			int minBase=2;
			for (int i=0;i<digits.length;i++) minBase=Math.max(minBase,digits[i]+1);

			int sol=-1;
			for (int base=minBase;base<=62 && sol==-1;base++) {
				BigInteger value=BigInteger.ZERO;
				BigInteger multi=BigInteger.ONE;
				for (int i=digits.length-1;i>=0;i--) {
					value=value.add(BigInteger.valueOf(digits[i]).multiply(multi));
					multi=multi.multiply(BigInteger.valueOf(base));
				}
				if (value.mod(BigInteger.valueOf(base-1)).equals(BigInteger.ZERO)) {
					sol=base;
					break;
				}
			}
			
			if (sol!=-1) System.out.println(sol);
			else System.out.println("such number is impossible!");
		}
	}
}
