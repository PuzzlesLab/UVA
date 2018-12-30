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
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
	}
	
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	private static int mst(int n, PriorityQueue<Edge> edges, int startIndex, int endIndex) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		int max=0;
		while (!edges.isEmpty() && getParent(parent, startIndex)!=getParent(parent, endIndex)) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				
				max=Math.max(e.dist, max);
			}
		}
		
		if (getParent(parent, startIndex)!=getParent(parent, endIndex)) max=-1;
		
		return max;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int C=Integer.parseInt(st.nextToken()), S=Integer.parseInt(st.nextToken()), Q=Integer.parseInt(st.nextToken());
			if (C==0 && S==0 && Q==0) break;
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int s=0;s<S;s++) {
				st=new StringTokenizer(br.readLine());
				edges.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			if (testCase>1) System.out.println();
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase++);
			sb.append('\n');
			
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				
				PriorityQueue<Edge> tempEdges=new PriorityQueue<>();
				tempEdges.addAll(edges);
				
				int sol=mst(C, tempEdges, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
				if (sol!=-1) sb.append(sol);
				else sb.append("no path");
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
		}
	}

}