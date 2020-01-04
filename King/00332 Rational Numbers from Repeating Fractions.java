import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

class Main {

	public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=1;
		while (!(s=br.readLine().trim()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(s);
			int J=Integer.parseInt(st.nextToken());
			String decimalStr=st.nextToken();
			double decimal=Double.parseDouble(decimalStr);
			int K=decimalStr.length()-2-J;
			
			int num, den, gcd;
			if (J>0) {
				int pow1=(int)Math.pow(10, K+J);
				int pow2=(int)Math.pow(10, K);
				BigDecimal bd1=BigDecimal.valueOf(pow1).multiply(BigDecimal.valueOf(decimal));
				BigDecimal bd2=BigDecimal.valueOf(pow2).multiply(BigDecimal.valueOf(decimal));
				bd1=bd1.add(bd2.remainder(BigDecimal.ONE));

				num=bd1.subtract(bd2).intValue();
				den=pow1-pow2;
			} else {
				num=BigDecimal.TEN.pow(K).multiply(BigDecimal.valueOf(decimal)).intValue();
				den=(int)Math.pow(10,K);
			}

			gcd=gcd(num,den);
			num/=gcd;
			den/=gcd;
			
			System.out.printf("Case %d: %s/%s\n", T++, num, den);
		}
	}

}