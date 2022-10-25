import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int to, cost;
		
		public Edge(int to, int cost) {
			this.to=to;
			this.cost=cost;
		}
		
		public int compareTo(Edge e) {
			return this.cost-e.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int V=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [C];
			for (int c=0;c<C;c++) adjList[c]=new ArrayList<>();
			for (int v=0;v<V;v++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				int cost=Integer.parseInt(st.nextToken());
				adjList[c1].add(new Edge(c2,cost));
				adjList[c2].add(new Edge(c1,cost));
			}

			int ans=-1;
			int [] dist=new int [C];
			Arrays.fill(dist,Integer.MAX_VALUE);
			dist[0]=0;
			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(new Edge(0,0));
			while (!q.isEmpty()) {
				Edge edge=q.poll();
				if (edge.cost>dist[edge.to]) continue;
				if (edge.to==C-1) {
					ans=dist[edge.to];
					break;
				}
				for (int i=0;i<adjList[edge.to].size();i++) {
					Edge ne1=adjList[edge.to].get(i);
					for (int i2=0;i2<adjList[ne1.to].size();i2++) {
						Edge ne2=adjList[ne1.to].get(i2);
						int ncost=edge.cost+ne1.cost+ne2.cost;
						if (ncost>=dist[ne2.to]) continue;
						dist[ne2.to]=ncost;
						q.offer(new Edge(ne2.to,ncost));
					}
				}
			}
			
			System.out.println(ans);
		}
	}

}
