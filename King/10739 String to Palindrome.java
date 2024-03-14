import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static String Text;
	private static int [][] Dp;
	private static int NULL=100000;

	private static int find(int i, int i2) {
		if (i>i2) return NULL;
		if (i==i2) return 0;
		if (Dp[i][i2]==NULL) {
			int ans=NULL+1;
			if (Text.charAt(i)==Text.charAt(i2)) {
				if (i+1==i2) ans=0;
				else ans=find(i+1,i2-1);
			} else {
				ans=Math.min(ans,1+find(i+1,i2));
				ans=Math.min(ans,1+find(i,i2-1));
				ans=Math.min(ans,1+find(i+1,i2-1));
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			Text=br.readLine();
			Dp=new int [Text.length()][Text.length()];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			int ans=find(0,Text.length()-1);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}
}