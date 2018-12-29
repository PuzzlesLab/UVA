import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y, dist;
		
		public Edge(int x, int y, int dist) {
			this.x=x-1;
			this.y=y-1;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) { return this.dist-e.dist;}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static int[] mst(int n, int ap, PriorityQueue<Edge> edges) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		int cost=0;
		while (!edges.isEmpty()) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py && e.dist<ap) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				cost+=e.dist;
			}
		}
		
		HashSet<Integer> region=new HashSet<>();
		for (int i=0;i<n;i++) region.add(getParent(parent, i));
		return new int [] {cost+ap*region.size(), region.size()};
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken()), A=Integer.parseInt(st.nextToken());
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				edges.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int [] ans=mst(N, A, edges);
			System.out.printf("Case #%d: %d %d\n", testCase, ans[0], ans[1]);
		}
	}

}