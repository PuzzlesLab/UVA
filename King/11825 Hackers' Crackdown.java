import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Links;
	private static int [] Combi;
	private static int [] Dp;
	private static int END_MASK;

	private static void doCombi(int n, int exp, int mask) {
		if (n==Links.length) {
			Combi[exp]=mask;
			return;
		}

		doCombi(n+1,exp,mask);
		doCombi(n+1,exp|(1<<n),mask|Links[n]);
	}
	
	private static int compute(int mask) {
		if (mask==0) return 0;

		if (Dp[mask]==-1) {
			int ans=0;
			for (int i=mask;i>0;i=(i-1)&mask) if (Combi[i]==END_MASK) { // If set/subset can cover all nodes.
				ans=Math.max(ans,1+compute(mask^i)); // Pick it, then add with unused exploit combi that can cover all nodes.
	    	}
			Dp[mask]=ans;
		}
		return Dp[mask];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	Links=new int [N];
        	for (int n=0;n<N;n++) {
        		Links[n]|=1<<n;
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		int M=Integer.parseInt(st.nextToken());
        		for (int m=0;m<M;m++) Links[n]|=1<<Integer.parseInt(st.nextToken());
        	}
        	
        	END_MASK=(1<<N)-1;
        	
        	Combi=new int [END_MASK+1];
        	doCombi(0,0,0);

        	Dp=new int [END_MASK+1];
        	Arrays.fill(Dp,-1);

        	System.out.printf("Case %d: %d\n",tc++,compute(END_MASK));
        }
	}

}