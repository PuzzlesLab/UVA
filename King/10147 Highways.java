import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	private static ArrayList<Edge> mst(int [] parent, PriorityQueue<Edge> edges) {
		ArrayList<Edge> list=new ArrayList<>();
		while (!edges.isEmpty()) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				list.add(e);
			}
		}
		return list;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			
			int [][] nodes=new int [N][2];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				nodes[n]=new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			int [] parent=new int [N];
			for (int i=0;i<N;i++) parent[i]=i;
			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int px=getParent(parent, Integer.parseInt(st.nextToken())-1), py=getParent(parent,Integer.parseInt(st.nextToken())-1);
				if (px>py) parent[px]=py;
				else parent[py]=px;
			}
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int n=0;n<N-1;n++) for (int n2=n+1;n2<N;n2++) if (getParent(parent,n)!=getParent(parent,n2)) {
				double dist=Math.pow(nodes[n][0]-nodes[n2][0],2)+Math.pow(nodes[n][1]-nodes[n2][1],2);
				edges.offer(new Edge(n,n2,dist));
			}
			
			if (testCase>0) System.out.println();
			ArrayList<Edge> needed=mst(parent, edges);
			if (needed.size()==0) System.out.println("No new highways need");
			else {
				StringBuilder sb=new StringBuilder();
				for (Edge e : needed) sb.append(String.format("%d %d\n", e.x+1, e.y+1));
				System.out.print(sb.toString());
			}
		}
	}

}
