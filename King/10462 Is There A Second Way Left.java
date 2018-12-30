import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
			return this.dist-e.dist;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static int mst(int N, ArrayList<Edge> edges, ArrayList<Edge> usedEdges) {
		int [] parent=new int [N];
		for (int v=0;v<N;v++) parent[v]=v;
		
		int cost=0;
		while (!edges.isEmpty()) {
			Edge e=edges.remove(0);
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				if (usedEdges!=null) usedEdges.add(e);
				cost+=e.dist;
			}
		}
		
		for (int i=1;i<parent.length;i++) if (getParent(parent,0)!=getParent(parent,i)) return Integer.MAX_VALUE;
		return cost;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int V=Integer.parseInt(st.nextToken()); int E=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edges=new ArrayList<>();
			for (int e=0;e<E;e++) {
				st=new StringTokenizer(br.readLine());
				edges.add(new Edge(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())));
			}
			Collections.sort(edges);
			
			ArrayList<Edge> tempEdges=new ArrayList<>(edges);
			ArrayList<Edge> usedEdges=new ArrayList<>();
			

			int best=mst(V, tempEdges, usedEdges);
			
			int secondBest=Integer.MAX_VALUE;
			if (best!=Integer.MAX_VALUE) {
				secondBest=Integer.MAX_VALUE;
				if (usedEdges.size()<edges.size()) {
					for (int i=0;i<usedEdges.size();i++) {
						tempEdges=new ArrayList<>(edges);
						tempEdges.remove(usedEdges.get(i));
						secondBest=Math.min(secondBest, mst(V, tempEdges, null));
					}
				} else secondBest=Integer.MAX_VALUE;
			}
			
			if (best==Integer.MAX_VALUE && secondBest==Integer.MAX_VALUE) System.out.printf("Case #%d : No way\n", testCase);
			else if (best!=Integer.MAX_VALUE && secondBest==Integer.MAX_VALUE)  System.out.printf("Case #%d : No second way\n", testCase);
			else System.out.printf("Case #%d : %d\n", testCase, secondBest);
		}
	}

}
