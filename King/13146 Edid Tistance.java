import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int NULL=1000000;
	private static char [] W1;
	private static char [] W2;
	private static int [][] Dp;

	private static int count(int i, int i2) {
		if (i==0 && i2==0) return 0;
		
		if (Dp[i][i2]==NULL) {
			int ans=NULL-1;
			if (i==0) ans=1+count(i,i2-1);
			else if (i2==0) ans=1+count(i-1,i2);
			else {
				ans=Math.min(ans,(W1[i-1]==W2[i2-1]?0:1)+count(i-1,i2-1));
				ans=Math.min(ans,1+count(i,i2-1));
				ans=Math.min(ans,1+count(i-1,i2));
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			W1=br.readLine().toCharArray();
			W2=br.readLine().toCharArray();
			
			Dp=new int [W1.length+1][W2.length+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.println(count(W1.length,W2.length));
		}
	}
}