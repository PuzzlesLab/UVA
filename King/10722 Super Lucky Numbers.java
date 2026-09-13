import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {

	private static int B;
	private static int N;
	private static BigInteger [][] Dp;

	private static BigInteger compute(int n, boolean lastOne) {
		if (n==N) return BigInteger.ONE;
		
		if (Dp[n][lastOne?1:0]==null) {
			BigInteger ans=compute(n+1,true); // Count put 1.

			BigInteger multi=null;
			// If first digit, exclude 0, 1 (counted).
			if (n==0) multi=BigInteger.valueOf(B-2);
			// If non-first digit:
			//   If last digit = 1, exclude 1 (counted), 3
			//   If last digit is not 1, exclude 1 (counted)
			else multi=BigInteger.valueOf(B-(lastOne?2:1));

			Dp[n][lastOne?1:0]=ans.add(multi.multiply(compute(n+1,false)));
		}
		return Dp[n][lastOne?1:0];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	B=Integer.parseInt(st.nextToken());
        	N=Integer.parseInt(st.nextToken());

        	Dp=new BigInteger [N][2];
        	System.out.println(compute(0,false));
        }
	}

}