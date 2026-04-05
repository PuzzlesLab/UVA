import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_ANS=1000000;
	private static int [][] Dist;
	private static int [][] Dp;
	private static int [] GroupMask;
	private static int [] GroupSize;
	private static int N;
	private static int Ans;

	private static int tsp(int curr, int visitMask) {
		if (visitMask==1) return Dist[curr][N+1];
		if (Dp[curr][visitMask]==-1) {
			int ans=MAX_ANS;
			for (int n=1;n<=N;n++) if ((visitMask&(1<<n))!=0) {
				ans=Math.min(ans,Dist[curr][n]+5+tsp(n,visitMask&~(1<<n)));
			}
			Dp[curr][visitMask]=ans;
		}
		return Dp[curr][visitMask];
	}

	private static void compute(int currN) {
		if (currN==N+1) {
			int time=0;
			for (int i=0;i<GroupMask.length;i++) time=Math.max(time,tsp(0,GroupMask[i]));
			Ans=Math.min(Ans,time);
			return;
		}
		for (int g=0;g<GroupMask.length;g++) if (GroupSize[g]<5) {
			GroupSize[g]++;
			GroupMask[g]|=1<<currN;
			compute(currN+1);
			GroupSize[g]--;
			GroupMask[g]&=~(1<<currN);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Dist=new int [N+2][N+2];
			for (int i=0;i<Dist.length;i++) Arrays.fill(Dist[i],MAX_ANS);
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				Dist[n1][n2]=Dist[n2][n1]=Math.min(Dist[n1][n2],Integer.parseInt(st.nextToken()));
			}
			for (int k=0;k<Dist.length;k++) for (int i=0;i<Dist.length;i++) for (int j=0;j<Dist.length;j++) Dist[i][j]=Math.min(Dist[i][j],Dist[i][k]+Dist[k][j]);

			int groups=N/5+(N%5!=0?1:0);
			GroupMask=new int [groups];
			Arrays.fill(GroupMask,1);
			GroupSize=new int [groups];

			Dp=new int [Dist.length][1<<Dist.length];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			Ans=MAX_ANS;
			compute(1);
			System.out.println(Ans);
		}
	}

}