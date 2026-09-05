import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Pos;
	private static double [] FinalPos;
	private static double W;
	private static double [][] Dp;

	private static double compute(int l, int r) {
		if (l==FinalPos.length&&r==FinalPos.length) return 0.0;
		if (l<0||r<0) return 10000000.0;

		if (Dp[l][r]==0.0) {
			double ans=10000000.0;
			int treeIdx=l+r;
			if (l<FinalPos.length) ans=Math.min(ans,Math.abs(FinalPos[l]-Pos[treeIdx])+compute(l+1,r));
			if (r<FinalPos.length) ans=Math.min(ans,Math.hypot(W,FinalPos[r]-Pos[treeIdx])+compute(l,r+1));
			Dp[l][r]=ans;
		}

		return Dp[l][r];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	int N=Integer.parseInt(s);
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	double L=Integer.parseInt(st.nextToken());
        	W=Integer.parseInt(st.nextToken());

        	Pos=new int [N];
        	for (int n=0;n<N;n++) Pos[n]=Integer.parseInt(br.readLine());
        	Arrays.sort(Pos);

        	int step=N>>1;
        	double lUnit=step==1?0.0:L/(step-1);
        	FinalPos=new double [step];
        	for (int n=0;n<FinalPos.length;n++) FinalPos[n]=n*lUnit;

        	Dp=new double [step+1][step+1];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1.0);
        	System.out.printf("%.10f\n",compute(0,0));
        }
	}

}