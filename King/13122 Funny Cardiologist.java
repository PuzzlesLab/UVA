import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static double NULL_VALUE=Double.MAX_VALUE/2;
	private static double INVALID_VALUE=Double.MAX_VALUE/4;
	private static int N;
	private static int K;
	private static int [][] Points;
	private static double [][] Dp;
	
	private static double find(int currN, int remK) {
		if (currN==N-1) return remK==0 ? 0.0 : INVALID_VALUE;  // Last point can't be removed.

		if (Dp[currN][remK]==NULL_VALUE) {
			double ans=INVALID_VALUE;
			for (int k=0;k<=remK;k++) {
				int nextN=currN+k+1;
				if (nextN>N-1) break;

				int dx=Points[currN][0]-Points[nextN][0];
				int dy=Points[currN][1]-Points[nextN][1];
				double dist=Math.sqrt(dx*dx+dy*dy);
				ans=Math.min(ans,dist+find(nextN,remK-k));
			}
			Dp[currN][remK]=ans;
		}
		return Dp[currN][remK];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());

			Points=new int [N][2];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Points[n][0]=Integer.parseInt(st.nextToken());
				Points[n][1]=Integer.parseInt(st.nextToken());
			}
			
			Dp=new double [N][K+1];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],NULL_VALUE);
			System.out.printf("%.3f\n", find(0,K));
		}
	}

}