import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static String Text;
	private static int [][] Dp;
	private static final int NULL=100000;

	private static boolean equalPart(String s, int s1, int s2, int len) {
		for (int i=0;i<len;i++) if (s.charAt(s1+i)!=s.charAt(s2+i)) return false;
		return true;
	}

	private static int count(int i, int i2) {
		if (i==i2) return 0;
		if (i+1==i2) return 1;
		if (Dp[i][i2]==NULL) {
			int ans=1+count(i+1,i2);

			int maxLen=(i2-i)/2;
			for (int len=1;len<=maxLen;len++) {
				int baseCase=count(i,i+len);
				int start=i+len;
				while (start+len<=i2) {
					if (!equalPart(Text,i,start,len)) break;
					ans=Math.min(ans,baseCase+count(start+len,i2));
					start+=len;
				}
			}
			
			Dp[i][i2]=ans;
		}
		return Dp[i][i2];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (!(Text=br.readLine()).equals("*")) {
			Dp=new int [Text.length()+1][Text.length()+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			System.out.println(count(0,Text.length()));
		}
	}
}