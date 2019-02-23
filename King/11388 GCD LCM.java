import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		BigInteger max=BigInteger.valueOf(Integer.MAX_VALUE);
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			BigInteger bi1=new BigInteger(st.nextToken());
			BigInteger bi2=new BigInteger(st.nextToken());
                        // bi1 * bi2 = gcd(bi1,bi2) * lcm(bi1,bi2)
			BigInteger gcd=bi1.gcd(bi2);
			BigInteger lcm=bi1.multiply(bi2).divide(gcd);
			
			if (gcd.compareTo(max)<=0 && lcm.compareTo(max)<=0 && gcd.compareTo(bi1)>=0) System.out.println(gcd.toString()+" "+lcm.toString());
			else System.out.println(-1);
		}
	}

}