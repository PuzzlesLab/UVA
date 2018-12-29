import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		String s;
		boolean nLine=false;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			for (int n=1;n<N;n++) br.readLine(); //useless
			
			PriorityQueue<Edge> mEdges=new PriorityQueue<>();
			PriorityQueue<Edge> mPlusKEdges=new PriorityQueue<>();
			
			int K=Integer.parseInt(br.readLine());
			for (int k=0;k<K;k++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				mPlusKEdges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Edge e=new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				mEdges.offer(e);
				mPlusKEdges.offer(e);
			}
			
			if (!nLine) nLine=true;
			else System.out.println("");
			
			System.out.println(mst(N, mEdges));
			System.out.println(mst(N, mPlusKEdges));
			br.readLine();
		}
	}

}