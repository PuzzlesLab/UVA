import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	private static class Station {
		int x, y;
		ArrayList<Edge> edgeList;
		
		public Station (int x, int y) {
			this.x=x;
			this.y=y;
			this.edgeList=new ArrayList<>();
		}
		
		public void addEdge(Edge e) {
			this.edgeList.add(e);
		}
		
		public void clearEdges() {
			Collections.sort(this.edgeList);
			while (this.edgeList.size()>2) this.edgeList.remove(this.edgeList.size()-1);
		}
	}
	
	private static class Edge implements Comparable<Edge> {
		Station src, dest;
		int dist;
		
		public Edge(Station s, Station d) {
			this.src=s;
			this.dest=d;
			int dx=s.x-d.x, dy=s.y-d.y;
			this.dist=dx*dx+dy*dy;
		}
		
		public int compareTo(Edge e) {
			if (this.dist!=e.dist) return this.dist-e.dist;
			else if (this.src.x!=e.src.x) return this.src.x-e.src.x;
			else return this.dest.y-e.dest.y;
		}
	}
	
	public static void visit(Station src, HashSet<Station> visited) {
		for (Edge e : src.edgeList) if (!visited.contains(e.dest)) {
			visited.add(e.dest);
			visit(e.dest, visited);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			ArrayList<Station> stations=new ArrayList<>();
			for (int n=0;n<N;n++) {
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				stations.add(new Station(x, y));
			}
			
			for (Station s1 : stations) for (Station s2 : stations) if (s1!=s2) s1.addEdge(new Edge(s1, s2));
			for (Station s1 : stations) s1.clearEdges();
				
			HashSet<Station> visited=new HashSet<>();
			visited.add(stations.get(0));
			visit(stations.get(0), visited);
			
			System.out.println((visited.size() == stations.size()) ? "All stations are reachable." : "There are stations that are unreachable.");
		}
	}

}