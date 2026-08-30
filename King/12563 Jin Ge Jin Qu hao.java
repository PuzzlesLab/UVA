import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int JGJQ_DUR=678;
	private static int N;
	private static int T;
	private static int [] Lengths;
	private static Solution [][] Dp;

	private static class Solution implements Comparable<Solution> {
		int songs, dur;
		
		public Solution(int s, int d) {
			this.songs=s;
			this.dur=d;
		}

		public int compareTo(Solution o) {
			if (this.songs!=o.songs) return this.songs-o.songs;
			return this.dur-o.dur;
		}
	}

	private static Solution find(int n, int t) {
		if (n==N || t<0) return new Solution(0,0);

		if (Dp[n][t]==null) {
			Solution ans=find(n+1,t);
			if (t+Lengths[n]<T) {
				Solution ans2=find(n+1,t+Lengths[n]);
				ans2.songs+=1;
				ans2.dur+=Lengths[n];

				if (ans2.compareTo(ans)>0) ans=ans2;
			}
			Dp[n][t]=ans;
		}
		return new Solution(Dp[n][t].songs,Dp[n][t].dur);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			T=Integer.parseInt(st.nextToken());
			Lengths=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) Lengths[n]=Integer.parseInt(st.nextToken());
			
			Dp=new Solution [N][T];
			
			Solution sol=find(0,0);
			int solMaxSong=sol.songs+1;
			int solEndTime=sol.dur+JGJQ_DUR;

			System.out.printf("Case %d: %d %d\n",tc,solMaxSong,solEndTime);
		}
	}

}