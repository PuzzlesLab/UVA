import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static final int MAX=1000000;
	private static final int NULL=MAX+1;
	private static int [] Dp;
	private static Tuple [] Tree;
	private static int N;
	
	private static int compute(int mask, int rem) {
		if (rem<=0) return 0;
		if (rem<=2) return 1;

		if (Dp[mask]==NULL) {
			int ans=MAX;
			for (int from=0;from<N;from++) if ((mask&(1<<from))==0) {
				for (int to=from+1;to<N;to++) if ((mask&(1<<to))==0) {
					int A=Tree[from].y-Tree[to].y;
					int B=Tree[to].x-Tree[from].x;
					int C=Tree[from].x*Tree[to].y-Tree[to].x*Tree[from].y;
	
					int newMask=mask;
					int count=0;
					for (int test=0;test<N;test++) if ((mask&(1<<test))==0) {
						if (A*Tree[test].x+B*Tree[test].y+C==0) {
							count++;
							newMask=newMask|(1<<test);
						}
					}
					if (count>0) ans=Math.min(ans,1+compute(newMask,rem-count));
				}
			}
			Dp[mask]=ans;
		}
		return Dp[mask];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			N=Integer.parseInt(br.readLine());
			int M=Integer.parseInt(br.readLine());
			
			Tree=new Tuple [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Tree[n]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			Dp=new int [2<<N];
			Arrays.fill(Dp,NULL);
			int ans=compute(0,M);

			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Case #");
			sb.append(tc);
			sb.append(":\n");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}
