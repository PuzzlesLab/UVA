import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Gangster implements Comparable<Gangster> {
		int t,p,s;
		
		public int compareTo(Gangster g) {
			return this.t-g.t;
		}
	}
	
	private static Gangster [] Gs;
	private static int [][] Dp;
	private static int N;
	private static int K;

	private static int compute(int n, int k) {
		if (k<0 || k>K || n>=N) return 0;

		if (Dp[n][k]==-1) {
			int ans=0;
			int nextN=0;

			for (int i=n;i<Gs.length&&Gs[i].t==Gs[n].t;i++) {
				if (Gs[i].s==k) ans+=Gs[i].p;
				nextN=i+1;
			}

			if (nextN<Gs.length) { // Proceed to next time
				int dT=Gs[nextN].t-Gs[n].t;
				int max=ans;
				// Enumerate all possible state changes within the time to next gangster.
				for (int i=0;i<=dT;i++) {
					max=Math.max(max,ans+compute(nextN,k+i));
					max=Math.max(max,ans+compute(nextN,k-i));
				}
				ans=max;
			}

			Dp[n][k]=ans;
		}
		return Dp[n][k];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken())+1;
			K=Integer.parseInt(st.nextToken());
			
			Gs=new Gangster[N];
			Gs[0]=new Gangster();
			st=new StringTokenizer(br.readLine());
			for (int n=1;n<N;n++) {
				Gs[n]=new Gangster();
				Gs[n].t=Integer.parseInt(st.nextToken());
			}
			st=new StringTokenizer(br.readLine());
			for (int n=1;n<N;n++) Gs[n].p=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for (int n=1;n<N;n++) Gs[n].s=Integer.parseInt(st.nextToken());
			Arrays.sort(Gs);

			Dp=new int [N][K+2];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			if (tc>0) sb.append('\n');
			sb.append(compute(0,0));
			sb.append('\n');
		}
		System.out.print(sb);
	}

}