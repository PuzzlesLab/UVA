import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int n;
		int distSq;
		
		public Edge(int n, int distSq) {
			this.n=n;
			this.distSq=distSq;
		}
	}

	private static class Tuple implements Comparable<Tuple> {
		int x, y;
		ArrayList<Edge> adjList;

		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
			this.resetAdjList();
		}

		public void resetAdjList() {
			this.adjList=new ArrayList<>();
		}

		public void addEdge(int n, int distSq) {
			this.adjList.add(new Edge(n,distSq));
		}

		public int compareTo(Tuple t) {
			return (this.x==t.x) ? this.y-t.y : this.x-t.x;
		}
		
		public int distSq(Tuple t) {
			int dx=this.x-t.x;
			int dy=this.y-t.y;
			return dx*dx+dy*dy;
		}
	}

	private static int N;
	private static Tuple [] Points;
	
	private static boolean reachable(double maxDistSq) {
		LinkedList<Integer> q=new LinkedList<>();
		q.addLast(N);
		boolean [] visited=new boolean [Points.length];
		visited[N]=true;
		while (!q.isEmpty()) {
			int curr=q.removeFirst();
			if (curr==N+1) return true;
			for (int i=0;i<Points[curr].adjList.size();i++) {
				Edge e=Points[curr].adjList.get(i);
				if (!visited[e.n] && e.distSq<=maxDistSq) {
					visited[e.n]=true;
					q.addLast(e.n);
				}
			}
		}
		return false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken()); // We don't need L.
			int W=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(br.readLine());

			Points=new Tuple [N+2];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Points[n]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Points[N]=new Tuple(0,0); // top wall
			Points[N+1]=new Tuple(0,W); // btm wall
			
			for (int n=0;n<N;n++) {
				Points[N].addEdge(n,Points[n].distSq(new Tuple(Points[n].x,0)));
				Points[n].addEdge(N+1,Points[n].distSq(new Tuple(Points[n].x,W)));
				for (int n2=n+1;n2<N;n2++) {
					int distSq=Points[n].distSq(Points[n2]);
					Points[n].addEdge(n2,distSq);
					Points[n2].addEdge(n,distSq);
				}
			}

			double minDSq=0;
			double maxDSq=W*W;
			int loop=0;
			while (Math.abs(maxDSq-minDSq)>1e-8 && loop++<40) {
				double midDSq=(minDSq+maxDSq)/2;
				if (reachable(midDSq)) maxDSq=midDSq;
				else minDSq=midDSq;
			}

			System.out.printf("Maximum size in test case %d is %.4f.\n",tc,Math.sqrt(maxDSq));
		}
	}

}