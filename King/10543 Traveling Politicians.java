import java.util.Scanner;

class Main {
	
	private static final int MAX_K=21;
	private static int N;
	private static int M;
	private static int K;
	private static boolean [][] AdjMat;
	private static int [][] Dp;

	private static boolean find(int n, int k) {
		if (k>=MAX_K) return false;

		if (Dp[n][k]==0) {
			boolean ans=(n==N-1);
			for (int next=0;next<AdjMat[n].length;next++) if (n!=next && AdjMat[n][next]) {
				ans|=find(next,k+1);
			}
			Dp[n][k]=ans?1:2;
		}
		return Dp[n][k]==1;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			try {
				N=sc.nextInt();
				M=sc.nextInt();
				K=sc.nextInt();
			} catch (Exception ex) { // Contains malicious input :/
				break;
			}
			if (N==0 && M==0 && K==0) break;
			
			AdjMat=new boolean [N][N];
			for (int m=0;m<M;m++) {
				AdjMat[sc.nextInt()][sc.nextInt()]=true;
			}

			Dp=new int[N][MAX_K+1];
			boolean reachable=find(0,1);
			if (reachable) {
				int ans=-1;
				for (int k0=K;k0<MAX_K;k0++) if (Dp[N-1][k0]==1) {
					ans=k0;
					break;
				}
				System.out.println(ans==-1 ? "LOSER" : ans);
			} else System.out.println("LOSER");

		}
	}

}