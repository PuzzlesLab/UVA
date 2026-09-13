import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int D;
	private static int G;
	private static long [][][][] Dp;
	private static boolean [][][][] DpFlag;

	private static long compute(int proj0D, int proj1D, int currProj, int culmul) {
	    if (culmul>G) {
	    	if (proj0D<D && currProj==1) return 0; // Left proj0 for too long.
	    	if (proj1D<D && currProj==0) return 0; // Left proj1 for too long.
	    }
	    if(proj0D==D && proj1D==D) return currProj==1 ? 1 : 0;

		if (!DpFlag[proj0D][proj1D][currProj][culmul]) {
			long ans=0;
			if (currProj==0) {
				// Continue.
				if (proj0D<D) ans+=compute(proj0D+1,proj1D,0,culmul+1);
				// Switch
				if (proj1D<D) ans+=compute(proj0D,proj1D+1,1,1);
			} else { // 1
				// Continue.
				if (proj1D<D) ans+=compute(proj0D,proj1D+1,1,culmul+1);
				// Switch
				if (proj0D<D) ans+=compute(proj0D+1,proj1D,0,1);
			}
			DpFlag[proj0D][proj1D][currProj][culmul]=true;
			Dp[proj0D][proj1D][currProj][culmul]=ans;
		}

		return Dp[proj0D][proj1D][currProj][culmul];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("-1 -1")) {
        	StringTokenizer st=new StringTokenizer(s);
        	D=Integer.parseInt(st.nextToken());
        	G=Integer.parseInt(st.nextToken());
        	
        	long ans=0; // D==0 && G==0
        	if (D==0) ans=G==0?1:0;
        	else {
            	Dp=new long [D+1][D+1][2][D+1];
            	DpFlag=new boolean [D+1][D+1][2][D+1];
            	ans=compute(1,0,0,1)<<1;
        	}

        	System.out.printf("Case %d: %d\n",tc++,ans);
        }
	}

}