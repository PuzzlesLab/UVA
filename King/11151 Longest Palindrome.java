import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static String Text;
	private static int [][] Dp;
	private static final int NULL=-100000;

	private static int find(int i, int i2) {
		if (i>i2) return 0;
		if (i==i2) return 1;
		if (Dp[i][i2]==NULL) {
			int ans=NULL+1;
			ans=Math.max(ans,find(i+1,i2));
			ans=Math.max(ans,find(i,i2-1));
			if (Text.charAt(i)==Text.charAt(i2)) {
				if (i+1==i2) ans=Math.max(ans,2);
				else ans=Math.max(ans,2+find(i+1,i2-1));
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			Text=br.readLine();
			Dp=new int [Text.length()][Text.length()];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.println(find(0,Text.length()-1));
		}
	}
}