import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge implements Comparable<Edge>{
		int x, y;
		double dist;
		
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
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int nodeCount=Integer.parseInt(br.readLine());
			double [][] c=new double [nodeCount][2];
			for (int n=0;n<nodeCount;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				c[n]=new double [] {Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())};
			}
			
			PriorityQueue<Edge> edges=new PriorityQueue<>();
			for (int i=0;i<nodeCount-1;i++) for (int i2=i+1;i2<nodeCount;i2++) {
				Edge e=new Edge();
				e.x=i;
				e.y=i2;
				double dx=c[i][0]-c[i2][0];
				double dy=c[i][1]-c[i2][1];
				e.dist=Math.sqrt(dx*dx+dy*dy);
				edges.offer(e);
			}
			
			int [] parent=new int [nodeCount];
			for (int i=0;i<nodeCount;i++) parent[i]=i;
			
			double ans=0.0;
			while (!edges.isEmpty()) {
				Edge e=edges.poll();
				int px=getParent(parent, e.x);
				int py=getParent(parent, e.y);
				if (px!=py) {
					if (px<py) parent[px]=parent[py];
					else if (px>py) parent[py]=parent[px];
					ans+=e.dist;
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.printf("%.2f\n", ans);
		}
	}

}
