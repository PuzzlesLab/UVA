import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	private static double mst(int n, PriorityQueue<Edge> edges) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		double max=0;
		while (!edges.isEmpty()) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				
				max=Math.max(e.dist, max);
			}
			if (getParent(parent, 0)==getParent(parent, 1)) break;
		}
		
		return max;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			int n=Integer.parseInt(br.readLine());
			if (n==0) break;
			int [][] nodes=new int [n][2];
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<nodes.length;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				nodes[i][0]=Integer.parseInt(st.nextToken());
				nodes[i][1]=Integer.parseInt(st.nextToken());
			}
			
			for (int i=0;i<nodes.length-1;i++) for (int i2=i+1;i2<nodes.length;i2++) {
				double dist=Math.sqrt(Math.pow(nodes[i][0]-nodes[i2][0], 2)+Math.pow(nodes[i][1]-nodes[i2][1], 2));
				edges.offer(new Edge(i, i2, dist));
			}

			System.out.printf("Scenario #%d\nFrog Distance = %.3f\n\n", testCase++, mst(n, edges));
			br.readLine();
		}
	}

}