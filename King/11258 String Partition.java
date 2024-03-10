import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static String Text;
	private static long [] Dp;
	private static final int NULL=-1;

	private static long count(int i) {
		if (i==Text.length()) return 0;
		if (Dp[i]==NULL) {
			long sol=0;
			for (int len=1;len<=i+10 && i+len<=Text.length();len++) {
				long v=Long.parseLong(Text.substring(i,i+len));
				if (v>Integer.MAX_VALUE) break;
				sol=Math.max(sol,v+count(i+len));
			}
			Dp[i]=sol;
		}
		return Dp[i];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			Text=br.readLine();
			Dp=new long [Text.length()+1];
			Arrays.fill(Dp,NULL);
			System.out.println(count(0));
		}
	}
}