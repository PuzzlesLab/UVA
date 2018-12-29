import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	private static int minRoll (String s1, String s2) {
		int count=0;
		for (int i=0;i<4;i++) {
			int a=s1.charAt(i)-'0';
			int b=s2.charAt(i)-'0';
			int s=Math.min(a, b);
			int l=Math.max(a, b);
			count+=Math.min(l-s, s+10-l);
		}
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			
			String [] node=new String [L];
			for (int l=0;l<node.length;l++) node[l]=st.nextToken();
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<node.length-1;i++) for (int i2=i+1;i2<node.length;i2++) edges.offer(new Edge(i, i2, minRoll(node[i], node[i2])));
			
			int initial=Integer.MAX_VALUE;
			for (int i=0;i<node.length;i++) initial=Math.min(initial, minRoll("0000", node[i]));
			
			System.out.println(initial+mst(node.length, edges));
		}
	}

}
