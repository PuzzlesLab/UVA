import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
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
		
		public int compareTo(Edge e) { return this.dist-e.dist;}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static int mst(int n, PriorityQueue<Edge> edges) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		int cost=0;
		while (!edges.isEmpty()) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				cost+=e.dist;
			}
		}
		
		for (int i=0;i<n;i++) if (getParent(parent, 0)!=getParent(parent, i)) {
			cost=-1;
			break;
		}
		return cost;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			if (s==0 && c==0) break;
			
			HashMap<String, Integer> map=new HashMap<>();
			for (int i=0;i<s;i++) map.put(br.readLine(), i);
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<c;i++) {
				st=new StringTokenizer(br.readLine());
				edges.offer(new Edge(map.get(st.nextToken()), map.get(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			br.readLine(); //not needed!
			
			int ans=mst(s, edges);
			if (ans>=0) System.out.println(ans);
			else System.out.println("Impossible");
		}
	}

}