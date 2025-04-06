import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static final double EPS=1e-6;

	private static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
		}

		public Point(int x, int y) {
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
			if (this.y!=p.y) return this.y-p.y;
			return this.x-p.x;
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
	
	private static boolean onLine(Point s, Point e, Point p) {
		return Math.abs(s.dist(e)-s.dist(p)-p.dist(e))<EPS;
	}

	private static double area(ArrayList<Point> points) {
		double a=0;
		for (int i=0;i<points.size();i++) a+=points.get(i).cross(points.get((i+1)%points.size()));
		return 0.5*Math.abs(a);
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		ArrayList<Point> result=new ArrayList<>();
		if (points.size()==0) return result;
		if (area(points)<EPS) {
			result.addAll(points);
			result.add(points.get(0));
			return result;
		}

		for (int i=0;i<points.size();i++) {
			Point p=points.get(i);
			while (result.size()>=2 && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			if (result.isEmpty() || result.get(result.size()-1)!=p) result.add(p);
		}
		int lim=result.size()+1;
		for (int i=points.size()-1;i>=0;i--) {
			Point p=points.get(i);
			while (result.size()>=lim && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) result.remove(result.size()-1);
			if (result.isEmpty() || result.get(result.size()-1)!=p) result.add(p);
		}

		return result;
	}

	private static boolean isInPolygon(ArrayList<Point> points, Point p) {
		if (points.size()<4) return false; // Minimum triangle = 4 points in cylic
		for (int i=0;i<points.size()-1;i++) if (points.get(i).compareTo(p)==0) return true;

		int count=0;
		for (int i=0;i<points.size()-1;i++) {
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			if (onLine(p1,p2,p)) return true;

			boolean f1=p1.y<=p.y && p.y<p2.y;
			boolean f2=p2.y<=p.y && p.y<p1.y;
			if (f1 || f2) {
				double m=(p2.x-p1.x)/((double)(p2.y-p1.y))*(p.y-p1.y);
				if (p.x-EPS<m+p1.x) count++;
			}
		}
		return (count&1)==1;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int C=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int O=Integer.parseInt(st.nextToken());
			if (C==0 && R==0 && O==0) break;

			ArrayList<Point> cops=new ArrayList<>();
			for (int c=0;c<C;c++) cops.add(new Point(br.readLine()));
			cops=convexHull(cops);
			ArrayList<Point> robbers=new ArrayList<>();
			for (int r=0;r<R;r++) robbers.add(new Point(br.readLine()));
			robbers=convexHull(robbers);

			StringBuilder sb=new StringBuilder();
			sb.append("Data set ");
			sb.append(TC++);
			sb.append(":\n");
			for (int o=0;o<O;o++) {
				Point citizen=new Point(br.readLine());
				boolean inCop=isInPolygon(cops,citizen);
				boolean inRob=isInPolygon(robbers,citizen);
				String status="neither";
				if (inCop) status="safe";
				else if (!inCop && inRob) status="robbed";

				sb.append("     Citizen at (");
				sb.append(citizen.x);
				sb.append(',');
				sb.append(citizen.y);
				sb.append(") is ");
				sb.append(status);
				sb.append(".\n");
			}
			System.out.println(sb.toString());
			br.readLine(); //Empty
		}
 	}

}
