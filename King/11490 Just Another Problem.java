import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class Main {

	private static BigInteger getArea(long a, long b) {
		//(3*b+2*a)*(2*b+a);
		BigInteger biA=BigInteger.valueOf(a);
		BigInteger biB=BigInteger.valueOf(b);
		BigInteger two=BigInteger.valueOf(2);
		BigInteger three=BigInteger.valueOf(3);
		
		BigInteger bi1=three.multiply(biB).add(two.multiply(biA));
		BigInteger bi2=two.multiply(biB).add(biA);
		return bi1.multiply(bi2);
	}

	public static void main(String[] args) throws Exception {
		BigInteger MOD=BigInteger.valueOf(100000007);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			long S=Long.parseLong(s);
			/*
			 * let :
			 * a = width of missing soldiers
			 * 2a^2 = number of missing soldiers
			 * b = thickness soldiers
			 * 
			 * S = Width + Height - 2a^2
			 *   = (3a+2a)(2b+a) - 2a^2
			 *
			 *
			 * After rearranging :
			 *      S - 6b^2
			 * a = -----------
			 *        7b
			 *        
			 * Try different values of b to get a, then we can get the original total.
			 */
			ArrayList<BigInteger> ans=new ArrayList<>();
			for (long b=1;b<=Math.sqrt(2*S);b++) {
				long a=S-6*b*b;
				if (a%(7*b)!=0 || a==b) continue; // Must be an integer & rectangle
				if (a<=0) break; //a will become smaller as iteration goes, skip remaining if a becomes invalid.
				a/=(7*b);

				ans.add(getArea(a,b).subtract(BigInteger.valueOf(S)).mod(MOD));
			}
			
			StringBuilder sb=new StringBuilder();
			if (ans.isEmpty()) sb.append("No Solution Possible\n");
			else {
				for (int i=0;i<ans.size();i++) {
					sb.append("Possible Missing Soldiers = ");
					sb.append(ans.get(i));
					sb.append('\n');
				}
			}
			System.out.println(sb.toString());
		}
	}

}
