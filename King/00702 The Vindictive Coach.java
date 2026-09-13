import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int M;
	private static long [][][] Dp;

	private static long compute(int down, int up, boolean toUp) {
		if (down==1 && up==1) return 1;
		if (down<1 || up<1) return 0;

		if (Dp[down][up][toUp?1:0]==-1) {
			long ans=0;
			
			int rMin=0;
			int rMax=0;
			if (!toUp) { // To go up, pick from min up to max down.
				rMin=up;
				rMax=down;
			} else { // To go down, pick from 1 to min up.
				rMin=1;
				rMax=up;
			}

			for (int i=rMin;i<rMax;i++) ans+=compute(down-1,i,!toUp);
			Dp[down][up][toUp?1:0]=ans;
		}

		return Dp[down][up][toUp?1:0];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	StringTokenizer st=new StringTokenizer(s);
        	N=Integer.parseInt(st.nextToken());
        	M=Integer.parseInt(st.nextToken());

        	if (N<=2) {
        		System.out.println(1);
        		continue;
        	}

        	Dp=new long [N+2][N+2][2];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp.length;i2++) Arrays.fill(Dp[i][i2],-1);
        	long ans=0;
        	if (M==1) ans=compute(N-1,2,true);
        	else for (int i=1;i<M;i++) ans+=compute(N-1,i,false);

        	System.out.println(ans);
        }
	}

}