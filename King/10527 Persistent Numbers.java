import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] digits=new BigInteger [10];
		for (int i=0;i<digits.length;i++) digits[i]=BigInteger.valueOf(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			if (s.length()==1) {
				System.out.println("1"+s);
				continue;
			}
			BigInteger N=new BigInteger(s);
			int [] digitsCount=new int [10];
			for (int i=digits.length-1;i>1;i--) {
				while (N.mod(digits[i]).equals(BigInteger.ZERO)) {
					N=N.divide(digits[i]);
					digitsCount[i]++;
				}
			}
			
			if (!N.equals(BigInteger.ONE)) {
				System.out.println("There is no such number.");
				continue;
			}
			
			StringBuilder sb=new StringBuilder();
			for (int i=2;i<digits.length;i++) {
				for (int i2=0;i2<digitsCount[i];i2++) sb.append(i);
			}
			System.out.println(sb.toString());
		}
	}

}
