import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][][] Dp;
	
	private static int compute(int remL, int start, int remS) {
		if (remL==0) return (remS==0) ? 1 : 0;
		
		if (Dp[remL][start][remS]==-1) {
			Dp[remL][start][remS]=0;
			for (int i=start;i<=26;i++) if (remS>=i) {
				Dp[remL][start][remS]+=compute(remL-1,i+1,remS-i);
			}
		}
		
		return Dp[remL][start][remS];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [27][28][352]; // Smax = N/2(a+l), where a=1, l=26
		for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);
			
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());

			// Keep Dp as it is, reusable in all test cases.
			int ans=(L<=26 && S<=351) ? compute(L,1,S) : 0;
			System.out.printf("Case %d: %d\n",testCase++,ans);
		}
	}

}
