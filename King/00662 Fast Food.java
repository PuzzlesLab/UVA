import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int INVALID=1000000;
	private static int [] Nums;
	private static int [][][] Dp;
	private static int [][][] DpDist;
	private static int [][][] TraceMid;
	private static int [][][] TraceDepot;

	private static int calcDist(int l, int r, int depot) {
		if (l>r) return 0;
		if (l==r) return Math.abs(Nums[depot]-Nums[l]);
		
		if (DpDist[l][r][depot]==-1) {
			int mid=(l+r)/2;
			DpDist[l][r][depot]=calcDist(l,mid,depot)+calcDist(mid+1,r,depot);
		}
		return DpDist[l][r][depot];
	}

	private static int compute(int l, int r, int k) {
		if (k==0) return (l>r) ? 0 : INVALID;

		if (Dp[l][r][k]==-1) {
			int min=INVALID;
			for (int mid=l;mid<=r;mid++) {
				int depot=(l+mid)/2;
				int sol=calcDist(l,mid,depot)+compute(mid+1,r,k-1);
				if (sol<min) {
					min=sol;
					TraceDepot[l][r][k]=depot;
					TraceMid[l][r][k]=mid;
				}
			}
			Dp[l][r][k]=min;
		}

		return Dp[l][r][k];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			Nums=new int [N];
			for (int n=0;n<N;n++) Nums[n]=Integer.parseInt(br.readLine());
			Dp=new int [N+1][N+1][K+1];
			for (int n=0;n<Dp.length;n++) for (int n2=0;n2<Dp[n].length;n2++) Arrays.fill(Dp[n][n2],-1);
			DpDist=new int [N+1][N+1][N+1];
			for (int n=0;n<DpDist.length;n++) for (int n2=0;n2<DpDist[n].length;n2++) Arrays.fill(DpDist[n][n2],-1);
			TraceMid=new int [N+1][N+1][K+1];
			TraceDepot=new int [N+1][N+1][K+1];

			int ans=compute(0,N-1,K);

			StringBuilder sb=new StringBuilder();
			sb.append("Chain ");
			sb.append(tc++);
			sb.append('\n');
			
			int currL=0;
			int currR=N-1;
			for (int k=K;k>0;k--) {
				sb.append("Depot ");
				sb.append((K-k+1));
				sb.append(" at restaurant ");
				sb.append(TraceDepot[currL][N-1][k]+1);
				sb.append(" serves restaurant");
				
				int next=TraceMid[currL][N-1][k];
				if (next==currL) {
					sb.append(' ');
					sb.append(currL+1);
				} else {
					sb.append("s ");
					sb.append(currL+1);
					sb.append(" to ");
					sb.append(next+1);
				}

				sb.append('\n');
				currL=TraceMid[currL][currR][k]+1;
			}

			sb.append("Total distance sum = ");
			sb.append(ans);
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
