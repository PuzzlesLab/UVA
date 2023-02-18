import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int NULL_VALUE=Integer.MAX_VALUE;
	private static int INVALID_VALUE=Integer.MIN_VALUE>>2;
	private static int [] Nodes;
	private static int [][] Dp;

	private static int find(int h, int n, int remK) {
		if (h==0) return (remK==1) ? Nodes[n] : INVALID_VALUE;
		if (remK==0) return 0;
		if (remK==1) return Nodes[n];

		if (Dp[n][remK]==NULL_VALUE) {
			int ans=INVALID_VALUE;
			int n1=n+1;
			int n2=n+(1<<h);
			for (int k=1;k<remK;k++) ans=Math.max(ans,find(h-1,n1,k)+find(h-1,n2,remK-k));
			Dp[n][remK]=ans;
		}
		return Dp[n][remK];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			StringTokenizer st=new StringTokenizer(s);
			int H=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			Nodes=new int [(2<<H)-1];
			for (int i=0;i<Nodes.length;i++) Nodes[i]=Integer.parseInt(st.nextToken());

			Dp=new int [Nodes.length][K+1];
			for (int i=0;i<Nodes.length;i++) Arrays.fill(Dp[i],NULL_VALUE);

			int ans=INVALID_VALUE;
			for (int k=1;k<=K;k++) ans=Math.max(ans,find(H,0,k));
			System.out.println(ans);
		}
	}

}
