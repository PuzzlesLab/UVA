import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y, dist;
		
		public Edge(int x, int y, int dist) {
			this.x=x-1;
			this.y=y-1;
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
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (true) {
			int N=sc.nextInt();
			int R=sc.nextInt();
			if (N==0 && R==0) break;
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int r=0;r<R;r++) edges.offer(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			
			int start=sc.nextInt(), end=sc.nextInt();
			double req=sc.nextInt();

			int minPass=mst(N, edges, start-1, end-1)-1;
			System.out.printf("Scenario #%d\nMinimum Number of Trips = %d\n\n", testCase++, (int)Math.ceil(req/minPass));
		}
	}

}
