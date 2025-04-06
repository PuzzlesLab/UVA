import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}
		
		public double dot(Point p) {
			return this.x*p.x+this.y*p.y;
		}
		
		public double square() {
			return this.x*this.x+this.y*this.y;
		}

		public double dist(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public int compareTo(Point p) {
			if (this.y!=p.y) return Double.compare(this.y,p.y);
			return Double.compare(this.x,p.x);
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static class Line {
		double a, b, c;
		Point p1, p2;

		public Line(Point p1, Point p2) {
			if (p1.x==p2.x) {
				this.a=1.0;
				this.b=0.0;
				this.c=-p1.x;
			} else {
				this.a=-(p1.y-p2.y)/(p1.x-p2.x);
				this.b=1.0;
				this.c=-(this.a*p1.x)-p1.y;
			}
			this.p1=p1;
			this.p2=p2;
		}

		public double distSegment(Point p) {
			Point sp=new Point(p.x-this.p1.x,p.y-this.p1.y);
			Point se=new Point(this.p2.x-this.p1.x,this.p2.y-this.p1.y);
			double u=sp.dot(se)/se.square();
			if (u<0.0) return p.dist(this.p1);
			if (u>1.0) return p.dist(this.p2);
			Point c=new Point(se.x*u,se.y*u);
			c.x+=this.p1.x;
			c.y+=this.p1.y;
			return p.dist(c);
		}

		public boolean isIntersect(Line l) {
			final double EPS=1e-6;
			boolean sameA=Math.abs(this.a-l.a)<EPS;
			boolean sameB=Math.abs(this.b-l.b)<EPS;
			boolean sameC=Math.abs(this.c-l.c)<EPS;
			if (sameA && sameB) { // Parallel
				if (sameC) { // Exact same line, check range
					return this.distSegment(l.p1)<EPS || this.distSegment(l.p2)<EPS
							|| l.distSegment(this.p1)<EPS || l.distSegment(this.p2)<EPS;
				}
				return false;
			}
			double ix=(l.b*this.c-this.b*l.c)/(l.a*this.b-this.a*l.b);
			double iy=0.0;
			if (Math.abs(this.b)>EPS) iy=-(this.a*ix+this.c);
			else iy=-(l.a*ix+l.c);
			Point ip=new Point(ix,iy);
			return this.distSegment(ip)<EPS && l.distSegment(ip)<EPS;
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
			boolean f1=p1.y<=p.y && p.y<p2.y;
			boolean f2=p2.y<=p.y && p.y<p1.y;
			if (f1 || f2) {
				double m=(p2.x-p1.x)/(p2.y-p1.y)*(p.y-p1.y);
				if (p.x-1e-6<m+p1.x) count++;
			}
		}
		return (count&1)==1;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			ArrayList<Point> vM=new ArrayList<>();
			for (int i=0;i<M;i++) {
				st=new StringTokenizer(br.readLine());
				vM.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			ArrayList<Point> vC=new ArrayList<>();
			for (int i=0;i<C;i++) {
				st=new StringTokenizer(br.readLine());
				vC.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			vM=convexHull(vM);
			vC=convexHull(vC);
			boolean intersect=false;
			for (int i=0;i<vM.size()-1 && !intersect;i++) intersect|=isPointInPolygon(vC,vM.get(i));
			for (int i=0;i<vC.size()-1 && !intersect;i++) intersect|=isPointInPolygon(vM,vC.get(i));
			for (int i=0;i<vM.size()-1 && !intersect;i++) {
				Line l1=new Line(vM.get(i),vM.get(i+1));
				for (int i2=0;i2<vC.size()-1 && !intersect;i2++) {
					Line l2=new Line(vC.get(i2),vC.get(i2+1));
					intersect|=l1.isIntersect(l2);
				}
			}
			System.out.println(intersect?"No":"Yes");
		}
 	}

}
