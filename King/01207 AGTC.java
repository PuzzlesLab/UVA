import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=100000;
	private static int [][] Dp;
	private static char [] S1;
	private static char [] S2;
	
	private static int count(int i, int i2) {
		if (i==0 && i2==0) return 0;
		
		if (Dp[i][i2]==NULL) {
			int ans=NULL-1;
			if (i==0) ans=1+count(i,i2-1);
			else if (i2==0) ans=1+count(i-1,i2);
			else {
				ans=Math.min(ans,(S1[i-1]==S2[i2-1]?0:1)+count(i-1,i2-1));
				ans=Math.min(ans,1+count(i,i2-1));
				ans=Math.min(ans,1+count(i-1,i2));
			}
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			st.nextToken(); // length
			S1=st.nextToken().toCharArray();
			
			st=new StringTokenizer(br.readLine());
			st.nextToken(); // length
			S2=st.nextToken().toCharArray();
			
			Dp=new int [S1.length+1][S2.length+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.println(count(S1.length,S2.length));
		}
	}
}