import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int NULL=10000000;
	private static int [][][] Dp=new int [11][101][101];
	private static boolean [][][] DpFlag=new boolean [11][101][101];
	
	private static int compute(int k, int l, int r) {
		if (l==r) return 0;
		if (k==0) return NULL;
		
		if (!DpFlag[k][l][r]) {
			int ans=NULL;
			for (int m=l+1;m<=r;m++) ans=Math.min(ans,m+Math.max(compute(k-1,l,m-1),compute(k,m,r)));
			Dp[k][l][r]=ans;
			DpFlag[k][l][r]=true;
		}
		return Dp[k][l][r];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int K=Integer.parseInt(st.nextToken());
        	int M=Integer.parseInt(st.nextToken());
        	System.out.printf("%d\n",compute(K,0,M));
        }
	}

}