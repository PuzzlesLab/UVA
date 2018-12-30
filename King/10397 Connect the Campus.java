import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

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
	
	private static double mst(int [] parent, PriorityQueue<Edge> edges) {
		double dist=0.0;
		while (!edges.isEmpty()) {
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
		Scanner sc=new Scanner(System.in);
		while (sc.hasNextInt()) {
			int N=sc.nextInt();
			int [][] nodes=new int [N][2];
			
			for (int n=0;n<N;n++) {
				nodes[n][0]=sc.nextInt();
				nodes[n][1]=sc.nextInt();;
			}
			
			int M=sc.nextInt();;
			int [] parent=new int [N];
			for (int i=0;i<N;i++) parent[i]=i;
			for (int m=0;m<M;m++) {
				int [] ints=new int [] {sc.nextInt()-1, sc.nextInt()-1};
				Arrays.sort(ints);
				parent[getParent(parent, ints[1])]=getParent(parent, ints[0]);
			}
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int n=0;n<N-1;n++) for (int n2=n+1;n2<N;n2++) {
				if (getParent(parent,n)!=getParent(parent,n2)) {
					double dist=Math.sqrt(Math.pow(nodes[n][0]-nodes[n2][0],2)+Math.pow(nodes[n][1]-nodes[n2][1], 2));
					edges.add(new Edge(n, n2, dist));
				}
			}
			
			System.out.printf("%.2f\n", mst(parent, edges));
		}
	}

}