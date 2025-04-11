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
		
		public double norm_sq() {
			return this.x*this.x+this.y*this.y;
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
	
	private static double distToLine(Point a, Point b, Point p) {
		Point vecAP=new Point(p.x-a.x,p.y-a.y);
		Point vecAB=new Point(b.x-a.x,b.y-a.y);
		double u=vecAP.dot(vecAB)/vecAB.norm_sq();
		return p.dist(new Point(a.x+vecAB.x*u,a.y+vecAB.y*u));
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			ArrayList<Point> ch=convexHull(points);
			double ans=999999.0;
			for (int i=0;i<ch.size()-1;i++) {
				Point left=ch.get(i);
				Point right=ch.get(i+1);
				double curr=-1.0;
				for (int n=0;n<points.size();n++) {
					Point p=points.get(n);
					if (p==left || p==right) continue;
					curr=Math.max(curr,distToLine(left,right,p));
				}
				if (curr>=0) ans=Math.min(ans,curr);
			}
			System.out.printf("Case %d: %.2f\n",tc++,ans);
		}
 	}

}
