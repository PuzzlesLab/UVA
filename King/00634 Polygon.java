import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Point {
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
	}
	
	private static class Vector {
		double x, y;
		
		public Vector(Point p1, Point p2) {
			this.x=p2.x-p1.x;
			this.y=p2.y-p1.y;
		}
		
		public double cross(Vector v) {
			return this.x*v.y-this.y*v.x;
		}
		
		public double dot(Vector v) {
			return this.x*v.x+this.y*v.y;
		}
		
		public double norm_sq() {
			return this.x*this.x+this.y*this.y;
		}
	}

	private static boolean ccw(Point p, Point q, Point r) {
		return new Vector(p,q).cross(new Vector(p,r))>1e-6;
	}

	private static double angle(Point a, Point o, Point b) {
		Vector oa=new Vector(o,a);
		Vector ob=new Vector(o,b);
		return Math.acos(oa.dot(ob)/Math.sqrt(oa.norm_sq()*ob.norm_sq()));
	}

	private static class Polygon {
		ArrayList<Point> points=new ArrayList<>();
		
		public void addPoint(Point p) {
			this.points.add(p);
		}
		
		public boolean contains(Point p) {
			if (points.size()<3) return false;
			
			double angle=0.0;
			for (int i=0;i<points.size();i++) {
				Point sp=points.get(i);
				Point ep=points.get((i+1)%points.size());
				double a1=sp.dist(p);
				double a2=p.dist(ep);
				double a3=sp.dist(ep);
				if (Math.abs(a1+a2-a3)<1e-6) return false;

				if (ccw(p,sp,ep)) angle+=angle(sp,p,ep);
				else angle-=angle(sp,p,ep);
			}
			return Math.abs(angle)>Math.PI;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Polygon polygon=new Polygon();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				polygon.addPoint(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			Point p=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			System.out.println(polygon.contains(p)?'T':'F');
		}
 	}

}