import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Router {
		int id, x, y, m;
		
		public Router(int id, int x, int y, int m) {
			this.id=id;
			this.x=x;
			this.y=y;
			this.m=m;
		}
	}

	private static class Edge implements Comparable<Edge> {
		Router r1, r2;
		double dist;
		
		private static double distBetween(Router r1, Router r2) {
			double dx=r1.x-r2.x;
			double dy=r1.y-r2.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public Edge(Router r1, Router r2) {
			this.r1=r1;
			this.r2=r2;
			this.dist=distBetween(r1,r2);
		}

		public int compareTo(Edge e) {
			return Double.compare(this.dist, e.dist);
		}
	}

	private static int getParent(int [] parent, int n) {
		if (parent[n]!=n) parent[n]=getParent(parent,parent[n]);
		return parent[n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Router [] routers=new Router[N];
			int totalM=0;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				routers[n]=new Router(n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				totalM+=routers[n].m;
			}

			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) edges.offer(new Edge(routers[n],routers[n2]));

			int [] parent=new int [N];
			int [] populations=new int [N];
			for (int n=0;n<N;n++) {
				parent[n]=n;
				populations[n]=routers[n].m;
			}

			double sum=0.0;
			while (!edges.isEmpty()) {
				Edge edge=edges.poll();
				int p1=getParent(parent,edge.r1.id);
				int p2=getParent(parent,edge.r2.id);
				if (p1!=p2) {
					if (p1==0) sum+=edge.dist*populations[p2];
					if (p2==0) sum+=edge.dist*populations[p1];

					if (p1<p2) {
						parent[p2]=p1;
						populations[p1]+=populations[p2];
					} else {
						parent[p1]=p2;
						populations[p2]+=populations[p1];
					}
				}
			}

			System.out.printf("Island Group: %d Average %.2f\n\n",tc++,sum/totalM);
		}
	}

}