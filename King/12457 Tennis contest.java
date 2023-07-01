import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			double p=Double.parseDouble(br.readLine());
			double q=1-p;

			double [][] dp=new double [N+1][N+1];
			dp[0][0]=1.0;

			for (int a=0;a<N;a++) for (int b=0;b<N;b++) {
				dp[a+1][b]+=dp[a][b]*p;
				dp[a][b+1]+=dp[a][b]*q;
			}
			
			double ans=0;
			for (int b=0;b<=N;b++) ans+=dp[N][b];
			System.out.printf("%.2f\n",ans);
		}
	}

}
