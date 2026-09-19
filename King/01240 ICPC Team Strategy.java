import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int INIT_REM_TIME=280;
	private static int [][] SolveTime;
	private static int N;
	private static int END_MASK;
	private static int [][][] Dp;
	
	private static int compute(int remTime, int qMask, int lastP) {
		if (remTime==0 || qMask==END_MASK) return 0;

		if (Dp[remTime][qMask][lastP]==-1) {
			int ans=0;
			for (int q=0;q<N;q++) if ((qMask&(1<<q))==0) {  // Find a new question
				for (int i=1;i<SolveTime.length;i++) if (i!=lastP && remTime>=SolveTime[i][q]) {  // Not last member + can solve within remaining time.
					ans=Math.max(ans,1+compute(remTime-SolveTime[i][q],qMask|1<<q,i));
				}
			}

			Dp[remTime][qMask][lastP]=ans;
		}
		return Dp[remTime][qMask][lastP];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	N=Integer.parseInt(br.readLine());
        	
        	SolveTime=new int [4][N];
        	for (int i=1;i<SolveTime.length;i++) {  // 0 = placeholder.
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		for (int n=0;n<N;n++) SolveTime[i][n]=Integer.parseInt(st.nextToken());
        	}

        	END_MASK=(1<<N)-1;
        	Dp=new int [INIT_REM_TIME+1][1<<N][4];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],-1);
        	System.out.println(compute(INIT_REM_TIME,0,0));
        }
	}

}