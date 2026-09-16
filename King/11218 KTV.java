import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int END_MASK=(1<<9)-1;
	private static int [] GroupMask;
	private static int [] GroupScore;
	private static boolean HasSol;
	private static int [][] Dp;

	private static int compute(int g, int pMask) {
		if (pMask==END_MASK) {
			HasSol=true;
			return 0;
		}
		if (g==GroupMask.length) return -1000000;
		
		if (Dp[g][pMask]==-1) {
			int ans=compute(g+1,pMask);
			int nPMask=pMask|GroupMask[g];
			if (Integer.bitCount(nPMask)==Integer.bitCount(pMask)+Integer.bitCount(GroupMask[g])) {
				ans=Math.max(ans,GroupScore[g]+compute(g,nPMask));
			}
			Dp[g][pMask]=ans;
		}

		return Dp[g][pMask];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	
        	GroupMask=new int [N];
        	GroupScore=new int [N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		int a=Integer.parseInt(st.nextToken())-1;
        		int b=Integer.parseInt(st.nextToken())-1;
        		int c=Integer.parseInt(st.nextToken())-1;
        		
        		GroupMask[n]=(1<<a)+(1<<b)+(1<<c);
        		GroupScore[n]=Integer.parseInt(st.nextToken());
        	}
        	
        	HasSol=false;
        	Dp=new int [N][1<<9];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        	int ans=compute(0,0);
        	if (!HasSol) ans=-1;

        	System.out.printf("Case %d: %d\n",tc++,ans);
        }
	}

}