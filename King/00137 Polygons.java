import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	private static final double EPS=1e-6;

	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
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
		Point p1, p2;

		public Line(Point p1, Point p2) {
			this.p1=p1;
			this.p2=p2;
			
		}
		
		public Point toVec() {
			return new Point(this.p2.x-this.p1.x,this.p2.y-this.p1.y);
		}

		public Point intersect(Line l) {
			Point v1=this.toVec();
			Point v2=l.toVec();
			double cross=v1.cross(v2);
			if (Math.abs(cross)<EPS) return null;
			double r1=new Point(l.p1.x-this.p1.x,l.p1.y-this.p1.y).cross(v2)/cross;
			double r2=new Point(l.p1.x-this.p1.x,l.p1.y-this.p1.y).cross(v1)/cross;
			if (r1>0 && r1<1 && r2>0 && r2<1) return new Point(this.p1.x+r1*v1.x,this.p1.y+r1*v1.y);
			return null;
		}
	}

	private static boolean ccw(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return vecOA.cross(vecOB)>0;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		ArrayList<Point> result=new ArrayList<>();

		for (int i=0;i<points.size();i++) {
			Point p=points.get(i);
			while (result.size()>=2 && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			result.add(p);
		}
		int lim=result.size()+1;
		for (int i=points.size()-1;i>=0;i--) {
			Point p=points.get(i);
			while (result.size()>=lim && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			result.add(p);
		}

		return result;
	}

	private static boolean isPointInPolygon(ArrayList<Point> points, Point p) {
		int count=0;
		for (int i=0;i<points.size()-1;i++) {
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			if (Math.abs(p1.dist(p2)-p1.dist(p)-p.dist(p2))<EPS) return true;

			boolean f1=p1.y<=p.y && p.y<p2.y;
			boolean f2=p2.y<=p.y && p.y<p1.y;
			if (f1 || f2) {
				double m=(p2.x-p1.x)*(p.y-p1.y)/(p2.y-p1.y);
				if (p.x-EPS<m+p1.x) count++;
			}
		}
		return (count&1)==1;
	}

	private static double area(ArrayList<Point> points) {
		double a=0.0;
		for (int i=0;i<points.size()-1;i++) a+=points.get(i).cross(points.get(i+1));
		return 0.5*Math.abs(a);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			if (N==0) break;
			ArrayList<Point> poly1=new ArrayList<>();
			for (int n=0;n<N;n++) poly1.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			poly1.add(poly1.get(0));
			Collections.reverse(poly1);
			double area1=area(poly1);
			
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			ArrayList<Point> poly2=new ArrayList<>();
			for (int n=0;n<N;n++) poly2.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			poly2.add(poly2.get(0));
			Collections.reverse(poly2);
			double area2=area(poly2);

			ArrayList<Point> intersect=new ArrayList<>();
			TreeSet<Point> treeSet=new TreeSet<>();
			for (int i=0;i<poly1.size()-1;i++) {
				Point p=poly1.get(i);
				if (isPointInPolygon(poly2,p) && !treeSet.contains(p)) {
					intersect.add(p);
					treeSet.add(p);
				}
			}
			for (int i=0;i<poly2.size()-1;i++) {
				Point p=poly2.get(i);

				if (isPointInPolygon(poly1,p) && !treeSet.contains(p)) {
					intersect.add(p);
					treeSet.add(p);
				}
			}
			for (int i=0;i<poly1.size()-1;i++) {
				Line l1=new Line(poly1.get(i),poly1.get(i+1));
				for (int i2=0;i2<poly2.size()-1;i2++) {
					Line l2=new Line(poly2.get(i2),poly2.get(i2+1));
					Point ip=l1.intersect(l2);
					if (ip!=null && !treeSet.contains(ip)) {
						intersect.add(ip);
						treeSet.add(ip);
					}
				}
			}
			intersect=convexHull(intersect);
			double area3=area(intersect);

			double ans=area1+area2-area3-area3;
			System.out.printf("%8.2f",ans);
		}
		System.out.println();
	}

}
