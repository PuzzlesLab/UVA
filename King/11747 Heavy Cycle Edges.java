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
		
		public int compareTo(Edge e) { return this.dist-e.dist;}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			PriorityQueue<Edge> unusedEdges=new PriorityQueue<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				edges.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			int [] parent=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			
			while (!edges.isEmpty()) {
				Edge e=edges.poll();
				int px=getParent(parent,e.x);
				int py=getParent(parent,e.y);
				if (px!=py) {
					if (px>py) parent[px]=py;
					else parent[py]=px;
				} else unusedEdges.offer(e);
			}
			
			if (unusedEdges.size()==0) System.out.println("forest");
			else {
				StringBuilder sb=new StringBuilder();
				while (!unusedEdges.isEmpty()) {
					sb.append(unusedEdges.poll().dist);
					sb.append(" ");
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
		}
	}

}
