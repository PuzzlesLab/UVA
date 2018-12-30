import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y;
		int dist;
		
		public Edge(int x, int y, int dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			return e.dist-this.dist;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static int mst(int n, PriorityQueue<Edge> edges, int startIndex, int endIndex) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		int min=Integer.MAX_VALUE;
		while (!edges.isEmpty() && getParent(parent, startIndex)!=getParent(parent, endIndex)) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				
				min=Math.min(e.dist, min);
			}
		}
		
		return min;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			if (n==0 && r==0) break;
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			HashMap<String, Integer> map=new HashMap<>();
			for (int i=0;i<r;i++) {
				st=new StringTokenizer(br.readLine());
				String c1=st.nextToken();
				if (!map.containsKey(c1)) map.put(c1, map.size());
				String c2=st.nextToken();
				if (!map.containsKey(c2)) map.put(c2, map.size());
				
				edges.offer(new Edge(map.get(c1),map.get(c2), Integer.parseInt(st.nextToken())));
			}
			
			st=new StringTokenizer(br.readLine());
			int startIndex=map.get(st.nextToken());
			int endIndex=map.get(st.nextToken());
			System.out.printf("Scenario #%d\n%d tons\n\n", testCase++, mst(n, edges, startIndex, endIndex));
		}
	}

}
