import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y, dist;
		
		public Edge (int x, int y, int dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine();
			StringTokenizer st=new StringTokenizer(s);
			int nodeCount=Integer.parseInt(st.nextToken());
			int edgeCount=Integer.parseInt(st.nextToken());
			if (nodeCount==0 && edgeCount==0) break;
		
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<edgeCount;i++) {
				st=new StringTokenizer(br.readLine());
				edges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int [] parent=new int [nodeCount];
			for (int i=0;i<nodeCount;i++) parent[i]=i;
			
			int totalDist=0, minDist=0;
			while (!edges.isEmpty()) {
				Edge e=edges.poll();
				totalDist+=e.dist;
				int px=getParent(parent, e.x);
				int py=getParent(parent, e.y);
				if (px!=py) {
					if (px>py) parent[px]=py;
					else parent[py]=px;
					minDist+=e.dist;
				}
			}
			System.out.println(totalDist-minDist);
		}
	}

}