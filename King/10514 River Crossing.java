import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public Tuple toVec(Tuple t) {
			return new Tuple(t.x-this.x,t.y-this.y);
		}
		
		public double dist(Tuple t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
		
		public double dot(Tuple t) {
			return this.x*t.x+this.y*t.y;
		}
		
		public double normSq() {
			return this.x*this.x+this.y*this.y;
		}
	}
	
	private static class Line {
		Tuple a, b;
		
		public Line(Tuple a, Tuple b) {
			this.a=a;
			this.b=b;
		}
		
		public double distTo(Tuple p) {
			Tuple ap=this.a.toVec(p);
			Tuple ab=this.a.toVec(this.b);
			double u=ap.dot(ab)/ab.normSq();
			return p.dist(new Tuple(this.a.x+u*ab.x,this.a.y+u*ab.y));
		}
		
		public double distToSegment(Tuple p) {
			Tuple ap=this.a.toVec(p);
			Tuple ab=this.a.toVec(this.b);
			double u=ap.dot(ab)/ab.normSq();
			if (u<0) return p.dist(this.a);
			else if (u>1) return p.dist(this.b);
			return this.distTo(p);
		}
	}

	private static class Area {
		Tuple [] points;
		boolean loop;
		Line [] lines;
		
		public Area(int size, boolean loop) {
			this.points=new Tuple[loop ? size+1 : size];
			this.loop=loop;
		}
		
		public void compile() {
			if (loop) this.points[this.points.length-1]=this.points[0];
			this.lines=new Line[this.points.length-1];
			for (int i=0;i<points.length-1;i++) this.lines[i]=new Line(this.points[i],this.points[i+1]);
		}

		public double dist(Area a) {
			double dist=10000000.0;
			// Point to polygon lines
			for (int i=0;i<this.points.length-1;i++) for (int i2=0;i2<a.lines.length;i2++) {
				dist=Math.min(dist,a.lines[i2].distToSegment(this.points[i]));
				if (dist==0) return dist;
			}
			// Polygon lines to point
			for (int i=0;i<a.points.length-1;i++) for (int i2=0;i2<this.lines.length;i2++) {
				dist=Math.min(dist,this.lines[i2].distToSegment(a.points[i]));
				if (dist==0) return dist;
			}
			return dist;
		}
	}
	
	private static class Edge implements Comparable<Edge> {
		int curr;
		double dist;
		
		public Edge(int curr, double dist) {
			this.curr=curr;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			return Double.compare(this.dist,e.dist);
		}
	}
	
	private static class Reader {
		BufferedReader br;
		StringTokenizer st;
		
		public Reader() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
			this.st=new StringTokenizer("");
		}
		
		public int nextInt() throws Exception {
			while (!this.st.hasMoreTokens()) this.st=new StringTokenizer(br.readLine());
			return Integer.parseInt(st.nextToken());
		}
	}

	public static void main(String [] args) throws Exception {
		final int START=0;
		final int END=1;
	
		Reader reader=new Reader();
		int TC=reader.nextInt();
		for (int tc=0;tc<TC;tc++) {
			int r1=reader.nextInt();
			int r2=reader.nextInt();
			int N=reader.nextInt();
			Area [] areas=new Area[N+2];

			areas[START]=new Area(r1,false);
			for (int i=0;i<r1;i++) areas[START].points[i]=new Tuple(reader.nextInt(),reader.nextInt());

			areas[END]=new Area(r2,false);
			for (int i=0;i<r2;i++) areas[END].points[i]=new Tuple(reader.nextInt(),reader.nextInt());

			for (int n=0;n<N;n++) {
				int V=reader.nextInt();
				areas[n+2]=new Area(V,true);
				for (int v=0;v<V;v++) {
					areas[n+2].points[v]=new Tuple(reader.nextInt(),reader.nextInt());
				}
			}
			for (int n=0;n<areas.length;n++) areas[n].compile();

			double [][] dist=new double [areas.length][areas.length];
			for (int n=0;n<areas.length;n++) for (int n2=n+1;n2<areas.length;n2++) dist[n][n2]=dist[n2][n]=areas[n].dist(areas[n2]);

			double [] lowestDist=new double [areas.length];
			Arrays.fill(lowestDist,10000000.0);
			lowestDist[START]=0.0;

			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(new Edge(START,0.0));
			while (!q.isEmpty()) {
				Edge e=q.poll();
				if (e.curr==END) break;
				for (int n=0;n<areas.length;n++) if (n!=e.curr) {
					double nDist=e.dist+dist[e.curr][n];
					if (nDist<lowestDist[n]) {
						lowestDist[n]=nDist;
						q.offer(new Edge(n,nDist));
					}
				}
			}
			System.out.printf("%.3f\n",lowestDist[END]);
		}
	}

}
