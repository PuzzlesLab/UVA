import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static final double EPS=1e-6;

	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double dist(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}

		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static class Line {
		double a, b, c;
		Point p1, p2;

		public Line(Point p1, Point p2) {
			this.p1=p1;
			this.p2=p2;

			if (p1.x==p2.x) {
				this.a=1.0;
				this.b=0;
				this.c=-p1.x;
			} else {
				this.a=(p2.y-p1.y)/(p1.x-p2.x);
				this.b=1.0;
				this.c=-(this.a*p1.x)-p1.y;
			}
		}
		
		public double getY(double x) {
			//ax+by+c=0;
			return -(c+this.a*x)/this.b;
		}

		public boolean isPointOnline(Point p) {
			double d2=this.p1.dist(p);
			double d3=p.dist(this.p2);
			if (d2==0 || d3==0) return false;
			return Math.abs(this.p1.dist(this.p2)-this.p1.dist(p)-p.dist(this.p2))<EPS;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			points.add(points.get(0));
			double threshold=Integer.parseInt(br.readLine());
			double x=Integer.parseInt(br.readLine());
			
			ArrayList<Double> yList=new ArrayList<>();
			for (int i=0;i<points.size()-1;i++) {
				Point p1=points.get(i);
				Point p2=points.get(i+1);
				if (p1.x==x && p2.x==x || p1.x==p2.x) continue;
				if (p1.y!=p2.y) {
					Line l=new Line(p1,p2);
					Point ip=new Point(x,l.getY(x));
					if (l.isPointOnline(ip)) yList.add(ip.y);
				}
			}
			Collections.sort(yList);

			double sum=0.0;
			for (int i=0;i<yList.size();i+=2) sum+=Math.abs(yList.get(i)-yList.get(i+1));
			System.out.println(sum>=threshold?"YES":"NO");
		}
	}

}