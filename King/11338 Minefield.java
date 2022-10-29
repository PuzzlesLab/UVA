import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		int id;
		double x, y;
		ArrayList<Edge> adjList;
		
		public Node(int id, double x, double y) {
			this.id=id;
			this.x=x;
			this.y=y;
			this.adjList=new ArrayList<>();
		}
	}

	private static class Edge implements Comparable<Edge> {
		Node to;
		double dist;

		public Edge(Node to, double dist) {
			this.to=to;
			this.dist=dist;
		}

		public int compareTo(Edge e) {
			return Double.compare(this.dist,e.dist);
		}
	}

	private static double distBetween(Node n1, Node n2) {
		double dx=n1.x-n2.x;
		double dy=n1.y-n2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("*")) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());

			int N=Integer.parseInt(br.readLine());
			Node [] nodes=new Node[N+2];
			nodes[0]=new Node(0,0,0);
			for (int n=1;n<=N;n++) {
				st=new StringTokenizer(br.readLine());
				nodes[n]=new Node(n,Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			nodes[N+1]=new Node(N+1,W,H);
			N+=2;

			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (n!=n2) {
				double db=distBetween(nodes[n],nodes[n2]);
				if (db<=1.5) nodes[n].adjList.add(new Edge(nodes[n2],db));
			}

			double maxWd=Double.parseDouble(br.readLine());

			double [] dist=new double [N];
			Arrays.fill(dist,Integer.MAX_VALUE);
			boolean ok=false;
			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(new Edge(nodes[0],0));
			dist[0]=0;
			while (!q.isEmpty()) {
				Edge e=q.poll();
				if (e.to==nodes[N-1]) {
					ok=true;
					break;
				}
				for (int i=0;i<e.to.adjList.size();i++) {
					Edge ne=e.to.adjList.get(i);
					double nd=e.dist+ne.dist;
					if (nd>=dist[ne.to.id]) continue;
					if (nd>maxWd) continue;
					dist[ne.to.id]=nd;
					q.offer(new Edge(ne.to,nd));
				}
			}
			
			System.out.println(ok?"I am lucky!":"Boom!");
		}
	}

}
