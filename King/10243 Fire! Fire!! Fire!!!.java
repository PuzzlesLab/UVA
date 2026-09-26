import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] AdjList;
	private static int [] Parent;
	private static int [][] Dp;


	private static int compute(int currN, int put) {
		if (AdjList[currN].length==0) return 0;

		if (Dp[currN][put]==-1) {
			int ans=put;
			for (int i=0;i<AdjList[currN].length;i++) {
				int next=AdjList[currN][i];
				if (Parent[currN]!=next) {
					Parent[next]=currN;
					if (put==0) ans+=compute(next,1);
					else ans+=Math.min(compute(next,0),compute(next,1));
				}
			}
			Dp[currN][put]=ans;
		}
		return Dp[currN][put];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	
        	AdjList=new int [N][];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		AdjList[n]=new int [Integer.parseInt(st.nextToken())];
        		for (int n2=0;n2<AdjList[n].length;n2++) AdjList[n][n2]=Integer.parseInt(st.nextToken())-1;
        	}

        	int ans=1;
        	if (N>1) {
            	Dp=new int [N][2];
            	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
            	Parent=new int [N];
            	Arrays.fill(Parent,-1);
            	ans=Math.min(compute(0,0),compute(0,1));
        	}

        	System.out.println(ans);
        }
	}

}