import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y, dist;
		
		public Edge(int x, int y, int dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			int [] delta= {this.dist-e.dist, this.x-e.x, this.y-e.y};
			for (int i=0;i<delta.length;i++) if (delta[i]!=0) return delta[i];
			return 0;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static ArrayList<Edge> mst(int n, PriorityQueue<Edge> edges) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		ArrayList<Edge> usedEdges=new ArrayList<>();
		while (!edges.isEmpty()) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				
				if (px>py) parent[px]=py;
				else parent[py]=px;
				usedEdges.add(e);
			}
		}
		
		return usedEdges;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine(), ",");
				for (int n2=0;n2<N;n2++) {
					int cost=Integer.parseInt(st.nextToken().trim());
					if (cost!=0) edges.offer(new Edge(n, n2, cost));
				}
			}
			
			ArrayList<Edge> usedEdges=mst(N, edges);
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			for (Edge e : usedEdges) {
				sb.append((char)('A'+e.x));
				sb.append('-');
				sb.append((char)('A'+e.y));
				sb.append(' ');
				sb.append(e.dist);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
