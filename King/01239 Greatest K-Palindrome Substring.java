import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static String Text;
	private static int [][] Dp;
	private static final int NULL=10000;

	private static int get(int start, int end) {
		if (start>=end) return 0;
		if (Dp[start][end]==NULL) Dp[start][end]=(Text.charAt(start)==Text.charAt(end)?0:1)+get(start+1,end-1);
		return Dp[start][end];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Text=st.nextToken();
			int K=Integer.parseInt(st.nextToken());
			int ans=0;
			
			Dp=new int [Text.length()][Text.length()];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			for (int start=0;start<Text.length();start++) {
				for (int end=start;end<Text.length();end++) {
					if (get(start,end)<=K) ans=Math.max(ans,end-start+1);
				}
			}
			System.out.println(ans);
		}
	}
}