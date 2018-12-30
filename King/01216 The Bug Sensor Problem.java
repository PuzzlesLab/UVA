package uva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class uva {
	
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
	
	//Use PriorityQueue or LinkedList. Using ArrayList.remove will run into time limit!
	private static ArrayList<Integer> mst(int N, PriorityQueue<Edge> edges) {
		int [] parent=new int [N];
		for (int v=0;v<N;v++) parent[v]=v;
		
		ArrayList<Integer> usedEdges=new ArrayList<>();
		while (!edges.isEmpty() && usedEdges.size()<N-1) {
			Edge e=edges.poll();
			int px=getParent(parent, e.x);
			int py=getParent(parent, e.y);
			if (px!=py) {
				if (px>py) parent[px]=py;
				else parent[py]=px;
				usedEdges.add((int)Math.ceil(e.dist));
			}
		}
		return usedEdges;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int S=Integer.parseInt(br.readLine());
			
			ArrayList<String> nodeStr=new ArrayList<>();
			while (true) {
				String l=br.readLine().trim();
				StringTokenizer st=new StringTokenizer(l);
				if (st.countTokens()<2) break;
				else nodeStr.add(l);
			}
			
			int [][] nodes=new int [nodeStr.size()][2];
			for (int i=0;i<nodes.length;i++) {
				StringTokenizer st=new StringTokenizer(nodeStr.get(i));
				nodes[i][0]=Integer.parseInt(st.nextToken());
				nodes[i][1]=Integer.parseInt(st.nextToken());
			}
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<nodes.length-1;i++) for (int i2=i+1;i2<nodes.length;i2++) {
				double dist=Math.sqrt(Math.pow(nodes[i][0]-nodes[i2][0], 2)+Math.pow(nodes[i][1]-nodes[i2][1], 2));
				edges.add(new Edge(i,i2,dist));
			}
			
			StringBuilder sb=new StringBuilder();
			if (S>=nodes.length) sb.append("0\n");
			else {
				ArrayList<Integer> usedEdges=mst(nodes.length,edges);
				sb.append(usedEdges.get(usedEdges.size()-S));
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
