import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static String Text;
	private static BigInteger [][] Dp;

	private static BigInteger find(int i, int i2) {
		if (i>i2) return BigInteger.ZERO;
		if (Dp[i][i2]==null) {
			BigInteger ans=(find(i+1,i2)).add(find(i,i2-1)).subtract(find(i+1,i2-1)); // Inclusion-exclusion principle.
			if (Text.charAt(i)==Text.charAt(i2)) ans=ans.add(BigInteger.ONE).add(find(i+1,i2-1));
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			Text=br.readLine();
			Dp=new BigInteger [Text.length()][Text.length()];
			System.out.println(find(0,Text.length()-1));
		}
	}
}