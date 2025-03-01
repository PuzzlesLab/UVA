import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;

			boolean [] Dp=new boolean [N+1];
			if (N<49) {
				Dp[1]=true;
				boolean [] lastJumped=new boolean [N+1];
				lastJumped[1]=true;
				for (int i=2;i<=(N+1)/2 && !Dp[M];i++) {
					int diff=2*i-1;
					boolean [] currJumped=new boolean [N+1];
					for (int n=1;n<=N && !Dp[M];n++) if (lastJumped[n]) {
						if (n-diff>=1) {
							Dp[n-diff]=true;
							currJumped[n-diff]=true;
						}
						if (n+diff<=N) {
							Dp[n+diff]=true;
							currJumped[n+diff]=true;
						}
					}
					lastJumped=currJumped;
				}
			} else Dp[M]=true;

			
			System.out.println(Dp[M]?"Let me try!":"Don't make fun of me!");
		}
	}

}
