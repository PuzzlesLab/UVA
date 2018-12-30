import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private static int mst(int n, ArrayList<Edge> edges, ArrayList<Edge> usedEdges) {
		int [] parent=new int [n];
		for (int i=0;i<n;i++) parent[i]=i;
		
		int dist=0;
		while (!edges.isEmpty()) {
			Edge e=edges.remove(0);
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				dist+=e.dist;
				if (usedEdges!=null) usedEdges.add(e);
			}
		}
		
		for (int i=1;i<n;i++) if (getParent(parent,0)!=getParent(parent, i)) return Integer.MAX_VALUE;
		return dist;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
			
			int [] edgesCount=new int [N];
			ArrayList<Edge> edges=new ArrayList<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Edge e=new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				edges.add(e);
				edgesCount[e.x]++;
				edgesCount[e.y]++;
			}
			Collections.sort(edges);
			
			ArrayList<Edge> tempEdges=new ArrayList<>(edges);
			ArrayList<Edge> usedEdges=new ArrayList<>();
			int ans1=mst(N, tempEdges, usedEdges);

			int ans2=Integer.MAX_VALUE;
			for (int i=0;i<usedEdges.size();i++) {
				tempEdges=new ArrayList<>(edges);
				tempEdges.remove(usedEdges.get(i));
				int sol=mst(N, tempEdges, null);
				ans2=Math.min(ans2, sol);
			}

			System.out.printf("%d %d\n", ans1, ans2);
		}
	}

}
