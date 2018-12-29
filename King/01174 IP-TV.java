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
		
		return cost;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int M=Integer.parseInt(br.readLine());
			int N=Integer.parseInt(br.readLine());
			
			int m=0;
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			HashMap<String, Integer> map=new HashMap<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String xs=st.nextToken();
				if (!map.containsKey(xs)) map.put(xs, m++);
				String ys=st.nextToken();
				if (!map.containsKey(ys)) map.put(ys, m++);
				
				edges.offer(new Edge(map.get(xs), map.get(ys), Integer.parseInt(st.nextToken())));
			}
			
			System.out.println(mst(M, edges));
			if (testCase<testCaseCount-1) System.out.println();
		}
	}

}