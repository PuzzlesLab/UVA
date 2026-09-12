import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Colors;
	private static int [][][] Dp;
	private static boolean [][][] DpFlag;

	private static int compute(int l, int r, int len) {
		if (l>r) return 0;
		if (l==r) return len*len;
		
		if (!DpFlag[l][r][len]) {
			int max=len*len+compute(l+1,r,1);
			for (int m=l+1;m<=r;m++) if (Colors[l]==Colors[m]) {
				max=Math.max(max,compute(l+1,m-1,1)+compute(m,r,len+1));
			}
			DpFlag[l][r][len]=true;
			Dp[l][r][len]=max;
		}
		
		return Dp[l][r][len];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	int N=Integer.parseInt(br.readLine());
        	Colors=new int [N];
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for (int n=0;n<N;n++) Colors[n]=Integer.parseInt(st.nextToken());

        	Dp=new int [N][N][N];
        	DpFlag=new boolean [N][N][N];
        	System.out.printf("Case %d: %d\n",tc,compute(0,N-1,1));
        }
	}

}