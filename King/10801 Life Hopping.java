import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Node {
		ArrayList<Edge> edges;
		int floor;
		
		public Node(int f) {
			this.floor=f;
			this.edges=new ArrayList<>();
		}
	}
	
	private static class Edge implements Comparable<Edge> {
		Node src, dest;
		int seconds;
		
		public Edge(Node s, Node d, int t) {
			this.src=s;
			this.dest=d;
			this.seconds=t;
		}
		
		public int compareTo(Edge e) { return this.seconds-e.seconds; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [] elevatorSpeed=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) elevatorSpeed[i]=Integer.parseInt(st.nextToken());
			
			ArrayList<Node> [] nodesByFloor=new ArrayList [100];
			for (int i=0;i<nodesByFloor.length;i++) nodesByFloor[i]=new ArrayList<>();
			
			for (int i=0;i<N;i++) {
				ArrayList<Integer> floors=new ArrayList<>();
				st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) floors.add(Integer.parseInt(st.nextToken()));
				//On each floor, create node
				Node [] nodes=new Node[floors.size()];
				for (int f=0;f<nodes.length;f++) {
					nodes[f]=new Node(floors.get(f));
					nodesByFloor[floors.get(f)].add(nodes[f]);
				}
				//Link nodes between floors in the same elevator
				for (int f=1;f<nodes.length;f++) {
					Node src=nodes[f];
					Node dest=nodes[f-1];
					src.edges.add(new Edge(src, dest, (floors.get(f)-floors.get(f-1))*elevatorSpeed[i]));
					dest.edges.add(new Edge(dest, src, (floors.get(f)-floors.get(f-1))*elevatorSpeed[i]));
				}
			}
			//Link nodes in the same floor but different elevator
			for (int i=0;i<nodesByFloor.length;i++) {
				ArrayList<Node> nodesOnFloor=nodesByFloor[i];
				for (Node src : nodesOnFloor) for (Node dest : nodesOnFloor) if (src!=dest) src.edges.add(new Edge(src, dest, 60));
			}
			
			int best=Integer.MAX_VALUE;
			for (Node n : nodesByFloor[0]) {
				PriorityQueue<Edge> q=new PriorityQueue<>();
				q.offer(new Edge(n,n,0));
				HashMap<Node, Integer> shortestSoFar=new HashMap<>();
				
				while (!q.isEmpty()) {
					Edge e=q.poll();
					if (e.dest.floor==K) {
						best=Math.min(best, e.seconds);
						break;
					}
					
					for (Edge next : e.dest.edges) {
						int nextSeconds = e.seconds+next.seconds;
						if (nextSeconds<shortestSoFar.getOrDefault(next.dest, Integer.MAX_VALUE)) {
							shortestSoFar.put(next.dest, nextSeconds);
							q.offer(new Edge(e.src, next.dest, nextSeconds));
						}
					}
				}
			}
			
			System.out.println((best!=Integer.MAX_VALUE) ? best : "IMPOSSIBLE");
		}
	}

}