import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [][][] Dp;
	private static boolean [][][] DpFlag;
	private static int Total;
	private static int C;

	private static int compute(int cCount, int remN5, int remN10) {
		if (cCount==C) return 0;

		if (!DpFlag[cCount][remN5][remN10]) {
			int remN1=Total-remN5*5-remN10*10-cCount*8;

			int ans=10000000;
			if (remN10>=1) ans=Math.min(ans,1+compute(cCount+1,remN5,remN10-1));
			if (remN10>=1 && remN1>=3) ans=Math.min(ans,4+compute(cCount+1,remN5+1,remN10-1));
			if (remN5>=2) ans=Math.min(ans,2+compute(cCount+1,remN5-2,remN10));
			if (remN5>=1 && remN1>=3) ans=Math.min(ans,4+compute(cCount+1,remN5-1,remN10));
			if (remN1>=8) ans=Math.min(ans,8+compute(cCount+1,remN5,remN10));
			Dp[cCount][remN5][remN10]=ans;
			DpFlag[cCount][remN5][remN10]=true;
		}

		return Dp[cCount][remN5][remN10];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	C=Integer.parseInt(st.nextToken());
        	int n1=Integer.parseInt(st.nextToken());
        	int n5=Integer.parseInt(st.nextToken());
        	int n10=Integer.parseInt(st.nextToken());
        	
        	Total=n1+n5*5+n10*10;
    		Dp=new int [C+1][300][300];
    		DpFlag=new boolean [C+1][300][300];
        	System.out.println(compute(0,n5,n10));
        }
	}

}