import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Main {

	private static class Node {
		char route, station;
		ArrayList<Edge> adjList;
		int idx;
		Node connParent;
		
		public Node(char r, char s) {
			this.route=r;
			this.station=s;
			this.adjList=new ArrayList<>();
			this.idx=((r-'A')*26)+(s-'A');
			this.connParent=this;
		}
	}

	private static class Edge implements Comparable<Edge> {
		Node to;
		int time;

		public Edge(Node to, int time) {
			this.to=to;
			this.time=time;
		}
		
		public int compareTo(Edge e) {
			return this.time-e.time;
		}
	}

	private static class SpAns {
		int dist;
		LinkedList<Node> stations;
		
		public SpAns(int dist, LinkedList<Node> stations) {
			this.dist=dist;
			this.stations=stations;
		}
	}

	private static String constructName(char route, char station) {
		StringBuilder sb=new StringBuilder();
		sb.append(route);
		sb.append(station);
		return sb.toString();
	}

	private static Node getConnParent(Node n) {
		if (n.connParent==n) return n;
		n.connParent=getConnParent(n.connParent);
		return n.connParent;
	}

	private static SpAns sp(Node start, Node end) {
		PriorityQueue<Edge> q=new PriorityQueue<>();
		int [] dist=new int [676];
		Node [] parent=new Node[676];
		Arrays.fill(dist,1000000);
		dist[start.idx]=0;
		q.offer(new Edge(start,0));
		while (!q.isEmpty()) {
			Edge e=q.poll();
			if (e.time>dist[e.to.idx]) continue;
			if (e.to==end) break;
			for (int i=0;i<e.to.adjList.size();i++) {
				Edge ne=e.to.adjList.get(i);
				int nt=e.time+ne.time;
				if (nt>=dist[ne.to.idx]) continue;
				parent[ne.to.idx]=e.to;
				dist[ne.to.idx]=nt;
				q.offer(new Edge(ne.to,nt));
			}
		}
		
		LinkedList<Node> stk=new LinkedList<>();
		Node temp=end;
		while (temp!=null) {
			stk.addFirst(temp);
			temp=parent[temp.idx];
		}
		return new SpAns(dist[end.idx],stk);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int R=Integer.parseInt(s.trim());
			HashMap<String,Node> nodeMap=new HashMap<>();
			ArrayList<Node> nodes=new ArrayList<>();
			for (int r=0;r<R;r++) {
				char [] ch=br.readLine().trim().toCharArray();
				char route=ch[0];
				ArrayList<Node> stations=new ArrayList<>();
				for (int i=2;i<ch.length;i++) {
					String name=constructName(route,ch[i]);
					Node station=null;
					if (!nodeMap.containsKey(name)) {
						station=new Node(route,ch[i]);
						nodeMap.put(name,station);
						nodes.add(station);
					} else station=nodeMap.get(name);
					stations.add(station);
					if (i+1<ch.length && ch[i+1]=='=') {
						String name2=constructName(ch[i+2],ch[i+3]);
						Node station2=null;
						if (!nodeMap.containsKey(name2)) {
							station2=new Node(ch[i+2],ch[i+3]);
							nodeMap.put(name2,station2);
							nodes.add(station2);
						} else station2=nodeMap.get(name2);
						if (station.idx<station2.idx) getConnParent(station2).connParent=getConnParent(station);
						else if (station.idx>station2.idx) getConnParent(station).connParent=getConnParent(station2);
						i+=3;
					}
				}

				for (int i=0;i<stations.size()-1;i++) {
					Node s1=stations.get(i);
					Node s2=stations.get((i+1)%stations.size());
					s1.adjList.add(new Edge(s2,1));
					s2.adjList.add(new Edge(s1,1));
				}
			}
			
			// Form connecting station edges
			for (int i=0;i<nodes.size();i++) {
				Node n1=nodes.get(i);
				for (int i2=0;i2<nodes.size();i2++) if (i!=i2) {
					Node n2=nodes.get(i2);
					if (getConnParent(n1)==getConnParent(n2)) n1.adjList.add(new Edge(n2,3));
				}
			}

			while (true) {
				s=br.readLine().trim();
				if (s.equals("#")) break;
				
				Node start=nodeMap.get(constructName(s.charAt(0),s.charAt(1)));
				Node end=nodeMap.get(constructName(s.charAt(2),s.charAt(3)));
				SpAns ans=sp(start,end);

				StringBuilder sb=new StringBuilder();
				sb.append(String.format("%3d",ans.dist));
				sb.append(": ");
				Node lastNode=null;
				while (!ans.stations.isEmpty()) {
					Node node=ans.stations.removeFirst();
					if (lastNode!=null && lastNode.route==node.route) sb.append(node.station);
					else {
						if (lastNode!=null) sb.append('=');
						sb.append(node.route);
						sb.append(node.station);
					}
					lastNode=node;
				}
				System.out.println(sb.toString());
			}
		}
	}

}