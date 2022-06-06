import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int UNDEFINED=-200000;
	private static final int NO_SOLUTION=-100000;
	private static int [][] score;
	private static int [][] dp;


	private static int find(int currN, int remM) {
		if (currN==score.length) return 0;
		if (remM==0) return NO_SOLUTION;
		
		if (dp[currN][remM]==UNDEFINED) {
			int curr=NO_SOLUTION;
			for (int m=remM;m>=1;m--) {
				if (score[currN][m]>=5) curr=Math.max(curr,score[currN][m]+find(currN+1, remM-m));
				else break;
			}
			dp[currN][remM]=curr;
		}

		return dp[currN][remM];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			score=new int [N][M+1];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int m=1;m<=M;m++) score[n][m]=Integer.parseInt(st.nextToken());
			}
			
			dp=new int [N][M+1];
			for (int n=0;n<N;n++) Arrays.fill(dp[n],UNDEFINED);

			int sol=find(0,M);
			if (sol<=0) System.out.println("Peter, you shouldn't have played billiard that much.");
			else System.out.printf("Maximal possible average mark - %.2f.\n", sol/(double)N);
		}
	}

}
