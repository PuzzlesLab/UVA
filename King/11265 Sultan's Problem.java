import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static final double EPS=1e-6;

	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Point p) {
			return this.x*p.y-p.x*this.y;
		}

		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
	}

	private static boolean ccw(Point p, Point q, Point r) {
		Point vPQ=new Point(q.x-p.x,q.y-p.y);
		Point vPR=new Point(r.x-p.x,r.y-p.y);
		return vPQ.cross(vPR)>0;
	}

	private static Point intersect(Point p, Point q, Point a, Point b) {
		double da=b.y-a.y;
		double db=a.x-b.x;
		double dc=b.x*a.y-a.x*b.y;
		double u=Math.abs(da*p.x+db*p.y+dc);
		double v=Math.abs(da*q.x+db*q.y+dc);
		return new Point((p.x*v+q.x*u)/(u+v),(p.y*v+q.y*u)/(u+v));
	}

	private static ArrayList<Point> cutPolygon(Point a, Point b, ArrayList<Point> points) {
		Point vAB=new Point(b.x-a.x,b.y-a.y);
		ArrayList<Point> result=new ArrayList<>();
		for (int i=0;i<points.size();i++) {
			Point p1=points.get(i);
			Point p2=points.get((i+1)%points.size());
			double left1=vAB.cross(new Point(p1.x-a.x,p1.y-a.y));
			double left2=0;
			if (i<points.size()-1) left2=vAB.cross(new Point(p2.x-a.x,p2.y-a.y));
			if (left1>-EPS) result.add(p1);
			if (left1*left2<-EPS) result.add(intersect(p1,p2,a,b));
		}
		if (!result.isEmpty() && result.get(0).compareTo(result.get(result.size()-1))!=0) result.add(result.get(0));
		return result;
	}

	private static double area(ArrayList<Point> points) {
		double area=0.0;
		for (int i=0;i<points.size()-1;i++) area+=points.get(i).cross(points.get(i+1));
		return 0.5*Math.abs(area);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			Point fountain=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

			ArrayList<Point> map=new ArrayList<>();
			map.add(new Point(0,0));
			map.add(new Point(W,0));
			map.add(new Point(W,H));
			map.add(new Point(0,H));
			map.add(map.get(0));

			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Point p1=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				Point p2=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				if (ccw(p1,p2,fountain)) map=cutPolygon(p1,p2,map);
				else map=cutPolygon(p2,p1,map);
			}
			System.out.printf("Case #%d: %.3f\n",tc++,area(map));
		}
	}

}