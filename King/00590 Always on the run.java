import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Context implements Comparable<Context> {
		int city, day, cost;
		
		public Context(int city, int day, int cost) {
			this.city=city;
			this.day=day;
			this.cost=cost;
		}
		
		public int compareTo(Context ctx) {
			return this.cost-ctx.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [][][] cost=new int [N][N][];
			for (int n1=0;n1<N;n1++) {
				for (int n2=0;n2<N;n2++) {
					if (n1==n2) continue;
					
					st=new StringTokenizer(br.readLine());
					int D=Integer.parseInt(st.nextToken());
					cost[n1][n2]=new int [D];
					for (int d=0;d<D;d++) cost[n1][n2][d]=Integer.parseInt(st.nextToken());
				}
			}

			int [][] minCost=new int [N][K];
			for (int n=0;n<N;n++) Arrays.fill(minCost[n],1000000);

			int ans=-1;
			PriorityQueue<Context> q=new PriorityQueue<>();
			q.offer(new Context(0,-1,0));
			while (!q.isEmpty()) {
				Context ctx=q.poll();
				if (ctx.day==K-1) {
					if (ctx.city==N-1) { // Reached at city n at day k.
						ans=ctx.cost;
						break;
					}
					continue; // We can't proceed further anymore.
				}
				for (int next=0;next<N;next++) {
					if (next==ctx.city) continue;
					int dIdx=(ctx.day+1)%cost[ctx.city][next].length;
					if (cost[ctx.city][next][dIdx]==0) continue; // cost 0 = no flight
					int nextCost=ctx.cost+cost[ctx.city][next][dIdx];
					if (nextCost<minCost[next][ctx.day+1]) {
						minCost[next][ctx.day+1]=nextCost;
						q.offer(new Context(next,ctx.day+1,nextCost));
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Scenario #");
			sb.append(testCase++);
			sb.append('\n');
			if (ans!=-1) {
				sb.append("The best flight costs ");
				sb.append(ans);
				sb.append('.');
			} else sb.append("No flight possible.");
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}
