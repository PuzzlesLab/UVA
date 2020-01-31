import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge> {
		int node, cost;
		public Edge(int n, int c) {
			this.node=n;
			this.cost=c;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost-o.cost;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			if (N==0 && M==0 && C==0 && K==0) break;
			
			ArrayList<Edge> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			
			int [][] distToEndHelper=new int [N][N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int U=Integer.parseInt(st.nextToken());
				int V=Integer.parseInt(st.nextToken());
				int P=Integer.parseInt(st.nextToken());
				adjList[U].add(new Edge(V,P));
				adjList[V].add(new Edge(U,P));
				if (U<C && V<C) distToEndHelper[Math.min(U,V)][Math.max(U, V)]=P;
			}
			
			int [] distToEnd=new int [C]; distToEnd[C-1]=0;
			for (int n=C-2;n>=0;n--) distToEnd[n]=distToEnd[n+1]+distToEndHelper[n][n+1];
			
			int [] minCost=new int [N];
			Arrays.fill(minCost,Integer.MAX_VALUE);
			
			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(new Edge(K,0));
			minCost[K]=0;
			while (!q.isEmpty()) {
				Edge e=q.poll();
				if (e.node==C-1) break;
				if (e.cost!=minCost[e.node]) continue;
				for (Edge ed : adjList[e.node]) {
					int nextCost=e.cost+ed.cost;
					if (nextCost<minCost[ed.node]) {
						if (ed.node<C) minCost[C-1]=Math.min(minCost[C-1], nextCost+distToEnd[ed.node]);
						else {
							minCost[ed.node]=nextCost;
							q.offer(new Edge(ed.node,minCost[ed.node]));
						}
					} 
				}
			}
			
			System.out.println(minCost[C-1]);
		}
	}

}