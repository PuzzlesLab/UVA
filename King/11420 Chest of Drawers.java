import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static long [][][] Dp;

	private static long compute(int remL, boolean lastLocked, int remS) {
		if (remL==0) return (remS==0) ? 1 : 0;
		if (remS<0) return 0;
		
		if (Dp[remL][lastLocked?1:0][remS]==-1) {
			long sum=0;
			sum+=compute(remL-1,true,lastLocked?remS-1:remS);
			sum+=compute(remL-1,false,remS);
			Dp[remL][lastLocked?1:0][remS]=sum;
		}

		return Dp[remL][lastLocked?1:0][remS];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			if (N<0 || S<0) return;
			
			Dp=new long [66][2][66];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);
			System.out.println(compute(N,true,S));
		}
	}

}