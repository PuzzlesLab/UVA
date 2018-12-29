import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y;
		double dist;
		
		public Edge(int x, int y, double dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			if (this.dist>e.dist) return 1;
			else if (this.dist==e.dist) return 0;
			return -1;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	public static double mst(int n, int [] parent, PriorityQueue<Edge> edges) {
		double dist=0.0;
		while (edges.size()>0) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				dist+=e.dist;
			}
		}
		return dist;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			
			int [][] nodes=new int [n][2];
			for (int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				nodes[i]=new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			double [][] dist=new double[n][n];
			int [] parent=new int[n];
			for (int i=0;i<n;i++) parent[i]=i;
			for (int i=0;i<n-1;i++) for (int i2=i+1;i2<n;i2++) {
				int dx=nodes[i][0]-nodes[i2][0];
				int dy=nodes[i][1]-nodes[i2][1];
				dist[i][i2]=Math.sqrt(dx*dx+dy*dy);
				
				if (dist[i][i2]<r) {
					int px=getParent(parent, i);
					int py=getParent(parent, i2);
					if (px!=py) {
						if (px>py) parent[px]=py;
						else parent[py]=px;
					}
				}
			}
			
			PriorityQueue<Edge> roadEdges=new PriorityQueue<>();
			PriorityQueue<Edge> railEdges=new PriorityQueue<>();
			for (int i=0;i<n-1;i++) for (int i2=i+1;i2<n;i2++) {
				if (getParent(parent, i)==getParent(parent, i2)) roadEdges.offer(new Edge(i, i2, dist[i][i2]));
				else railEdges.offer(new Edge(i, i2, dist[i][i2]));
			}
			
			HashSet<Integer> stateSet=new HashSet<>();
			for (int i=0;i<n;i++) stateSet.add(getParent(parent, i));
			
			for (int i=0;i<n;i++) parent[i]=i;
			int roadCount=(int)(mst(n, parent, roadEdges)+0.5);
			int railCount=(int)(mst(n, parent, railEdges)+0.5);
			
			System.out.printf("Case #%d: %d %d %d\n", testCase, stateSet.size(), roadCount, railCount);
		}
	}

}
