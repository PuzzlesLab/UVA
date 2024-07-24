import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static BigInteger [][] Dp=new BigInteger [101][64];

	private static BigInteger getFloor(int n, int d) {
		if (n==0) return BigInteger.ZERO;
		if (d==1) return BigInteger.ONE;
		if (Dp[n][d]==null) Dp[n][d]=BigInteger.ONE.add(getFloor(n-1,d-1)).add(getFloor(n,d-1));
		return Dp[n][d];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			BigInteger K=new BigInteger(st.nextToken());
			
			if (n==0) break;
			
			int sol=-1;
			for (int d=1;d<64;d++) {
				BigInteger curr=getFloor(n,d);
				if (curr.compareTo(K)>=0) {
					sol=d;
					break;
				}
			}
			
			System.out.println(sol!=-1?sol:"More than 63 trials needed.");
		}
	}

}
