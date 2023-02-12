import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int H;
	private static int [] InitFish;
	private static int [] DecayFish;
	private static int [] TTime;
	private static int [][] Dp;
	private static Context [][] Solution;

	private static class Context {
		int lake, h, stayTime;
		
		public Context(int l, int h, int st) {
			this.lake=l;
			this.h=h;
			this.stayTime=st;
		}
	}

	private static int find(int lake, int h) {
		if (h>=H || lake>N) return 0;

		if (Dp[lake][h]==-1) {
			int ans=0;
			int fish=InitFish[lake];
			int tempH=h;
			int fishCaught=0;
			while (tempH<=H) {
				int currAns=fishCaught+find(lake+1,tempH+TTime[lake+1]);
				if (currAns>=ans) {
					ans=currAns;
					Solution[lake][h]=new Context(lake+1,tempH+TTime[lake+1],(tempH-h)*5);
				}
				tempH++;
				fishCaught+=fish;
				fish=Math.max(0, fish-DecayFish[lake]);
			}
			Dp[lake][h]=ans;
		}
		return Dp[lake][h];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			H=Integer.parseInt(br.readLine())*60/5;

			InitFish=new int [N+1];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) InitFish[n]=Integer.parseInt(st.nextToken());

			DecayFish=new int [N+1];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) DecayFish[n]=Integer.parseInt(st.nextToken());
			
			TTime=new int [N+1];
			st=new StringTokenizer(br.readLine());
			for (int n=1;n<N;n++) TTime[n]=Integer.parseInt(st.nextToken());

			Dp=new int[N+1][H+1];
			Solution=new Context [N+1][H+1];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);
			int ans=find(0,0);

			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			
			Context ctx=Solution[0][0];
			for (int n=0;n<N;n++) {
				sb.append(ctx.stayTime);
				sb.append(", ");
				if (ctx.h>=H+1) ctx.stayTime=0; // Ended, just print remaining 0s
				else ctx=Solution[ctx.lake][ctx.h];
			}

			sb.setLength(sb.length()-2);
			sb.append("\nNumber of fish expected: ");
			sb.append(ans);
			System.out.println(sb.toString());
		}
	}

}
