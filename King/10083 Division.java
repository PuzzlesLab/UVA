import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int t=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			int digits=(int)Math.ceil((a-b)*Math.log10(t));
			
			StringBuilder sb=new StringBuilder();
			sb.append(String.format("(%d^%d-1)/(%d^%d-1) ",t,a,t,b));
			
			if (t==1 || a<b || a%b!=0 || digits>=100) sb.append("is not an integer with less than 100 digits.");
			else if (a==b) sb.append(1);
			else {
				BigInteger biT=BigInteger.valueOf(t);
				BigInteger biBtm=biT.pow(b).subtract(BigInteger.ONE);
				BigInteger biTop=biT.pow(a).subtract(BigInteger.ONE);
				sb.append(biTop.divide(biBtm));
			}
			System.out.println(sb.toString());
		}
	}
}