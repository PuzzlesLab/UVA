import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static BigInteger sqrt(BigInteger x) {
		BigInteger min=BigInteger.ZERO;
		BigInteger max=x;
		BigInteger mid=null;
		while (min.compareTo(max)<=0) {			
			mid=min.add(max).shiftRight(1);
			BigInteger midSq=mid.multiply(mid);
			int cmp=midSq.compareTo(x);
			if (cmp==0) return mid;
			else if (cmp<0) min=mid.add(BigInteger.ONE);
			else max=mid.subtract(BigInteger.ONE);
		}
		return max;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			BigInteger bi=new BigInteger(br.readLine());
			if (bi.equals(BigInteger.ZERO)) break;
			
			String sqrt=sqrt(bi).toString();
			StringBuilder sb=new StringBuilder();
			sb.append(sqrt.charAt(0));
			for (int i=1;i<sqrt.length();i++) sb.append('0');
			
			System.out.println(sb.toString());
		}
		
	}

}