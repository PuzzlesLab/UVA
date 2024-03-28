import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		final int MOD=10007;
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String s1=st.nextToken();
			String s2=st.nextToken();
			String s3=st.nextToken();

			int [][][][] dp=new int [s1.length()+1][s2.length()+1][s3.length()+1][3];

			for (int i=0;i<=s1.length();i++) for (int i2=0;i2<=s2.length();i2++) {
				dp[i][i2][0][0]=1;
				dp[i][i2][0][1]=1;
				dp[i][i2][0][2]=1;
			}

			for (int i3=1;i3<=s3.length();i3++) {
				for (int i=0;i<=s1.length();i++) for (int i2=0;i2<=s2.length();i2++) {
					if (i>0) {
						dp[i][i2][i3][0]=dp[i-1][i2][i3][0];
						if (s1.charAt(i-1)==s3.charAt(i3-1)) dp[i][i2][i3][0]=(dp[i][i2][i3][0]+dp[i-1][i2][i3-1][2])%MOD;
					}
					if (i2>0) {
						dp[i][i2][i3][1]=dp[i][i2-1][i3][1];
						if (s2.charAt(i2-1)==s3.charAt(i3-1)) dp[i][i2][i3][1]=(dp[i][i2][i3][1]+dp[i][i2-1][i3-1][2])%MOD;
					}
					dp[i][i2][i3][2]=(dp[i][i2][i3][0]+dp[i][i2][i3][1])%MOD;
				}
			}

			System.out.println(dp[s1.length()][s2.length()][s3.length()][2]);
		}
	}
}