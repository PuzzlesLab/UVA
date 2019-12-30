import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	private static BigInteger reverse(BigInteger bi, int base) {
		StringBuilder sb=new StringBuilder();
		char [] c=bi.toString(base).toCharArray();
		for (int i=c.length-1;i>=0;i--) sb.append(c[i]);
		return new BigInteger(sb.toString(),base);
	}
	
	private static boolean isPalindrome(BigInteger bi, int base) {
		char [] c=bi.toString(base).toCharArray();
		for (int i=0;i<c.length/2;i++) if (c[i]!=c[c.length-1-i]) return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			for (int i=15;i>=2;i--) {
				try {
					int step=0;
					BigInteger bi=new BigInteger(s,i);
					if (!isPalindrome(bi,i)) {
						while (true) {
							step++;
							BigInteger reverseBi=reverse(bi,i);
							BigInteger sum=bi.add(reverseBi);
							if (isPalindrome(sum,i)) break;
							bi=sum;
						}
					}
					sb.append(step);
				} catch (Exception e) {
					sb.append('?');
				}
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}