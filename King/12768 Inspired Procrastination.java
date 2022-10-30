import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int to, point;
		
		public Edge(int to, int point) {
			this.to=to;
			this.point=point;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				int F=Integer.parseInt(st.nextToken());
				adjList[A].add(new Edge(B,-F));
			}
			
			int [] dist=new int [N];
			Arrays.fill(dist,Integer.MAX_VALUE);
			dist[0]=0;
			for (int i=0;i<N-1;i++) {
				for (int from=0;from<N;from++) {
					if (dist[from]==Integer.MAX_VALUE) continue;
					for (Edge e: adjList[from]) {
						dist[e.to]=Math.min(dist[e.to], dist[from]+e.point);
					}
				}
			}
			
			boolean hasCycle=false;
			for (int from=0;from<N && !hasCycle;from++) {
				if (dist[from]==Integer.MAX_VALUE) continue;
				for (Edge e: adjList[from]) {
					int attempt=dist[from]+e.point;
					if (attempt<dist[e.to]) {
						hasCycle=true;
						break;
					}
				}
			}

			if (hasCycle) System.out.println("Unlimited!");
			else {
				int ans=0;
				for (int i=0;i<N;i++) ans=Math.min(ans,dist[i]);
				ans=Math.abs(ans);
				System.out.println(ans);
			}
		}
	}

}
