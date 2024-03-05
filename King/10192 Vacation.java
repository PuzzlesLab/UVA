import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	private static char [] Left;
	private static char [] Right;
	private static int [][] Dp;
	private static final int NULL=-10000;

	private static int find(int l, int r) {
		if (l==Left.length || r==Right.length) return 0;
		if (Dp[l][r]==NULL) {
			int ans=0;
			ans=Math.max(ans,find(l+1,r));
			ans=Math.max(ans,find(l,r+1));
			if (Left[l]==Right[r]) ans=Math.max(ans,1+find(l+1,r+1));
			Dp[l][r]=ans;
		}
		return Dp[l][r];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("#")) {
			Left=s.toCharArray();
			Right=br.readLine().toCharArray();
			
			Dp=new int [Left.length][Right.length];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.printf("Case #%d: you can visit at most %d cities.\n", tc++, find(0,0));
		}
	}
}