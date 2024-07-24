import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int next, len;
		
		public Edge(int n, int l) {
			this.next=n;
			this.len=l;
		}
	}

	private static int N;
	private static int [][] MinDist;
	private static int [] Dp;
	
	private static int findMin(int mask) {
		if (mask==0) return 0;
		if (Dp[mask]==-1) {
			int sol=1000000;
			for (int n=0;n<N;n++) {
				if ((mask&(1<<n))==0) continue;
				for (int n2=n+1;n2<N;n2++) {
					if ((mask&(1<<n2))==0) continue;
					sol=Math.min(sol,MinDist[n][n2]+findMin(mask-((1<<n)+(1<<n2))));
				}
			}
			Dp[mask]=sol;
		}
		return Dp[mask];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			ArrayList<Edge> [] edges=new ArrayList [N];
			for (int n=0;n<N;n++) edges[n]=new ArrayList<>();
			int ans=0;
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				int l=Integer.parseInt(st.nextToken());
				edges[n1].add(new Edge(n2,l));
				edges[n2].add(new Edge(n1,l));
				ans+=l;
			}
			
			MinDist=new int [N][N];
			for (int n=0;n<N;n++) {
				Arrays.fill(MinDist[n], 1000000);
				MinDist[n][n]=0;
				for (int i=0;i<edges[n].size();i++) {
					int next=edges[n].get(i).next;
					MinDist[n][next]=Math.min(MinDist[n][next],edges[n].get(i).len);
				}
			}
			
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				MinDist[i][j]=Math.min(MinDist[i][j], MinDist[i][k]+MinDist[k][j]);
			}

			int mask=0;
			Dp=new int [(1<<N)+1];
			Arrays.fill(Dp,-1);
			// Find set of odd-degree node pairs with lowest length.
			for (int n=0;n<N;n++) if ((edges[n].size()&1)==1) mask|=(1<<n);
			ans+=findMin(mask);
			System.out.println(ans);
		}
	}

}