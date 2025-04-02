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

		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
	}

	private static double ccw(Point p, Point q, Point r) {
		Point vecPQ=new Point(q.x-p.x,q.y-p.y);
		Point vecPR=new Point(r.x-p.x,r.y-p.y);
		return vecPQ.cross(vecPR);
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		ArrayList<Point> result=new ArrayList<>();

		for (int i=0;i<points.size();i++) {
			int rSize=result.size();
			while ((rSize>=2) && ccw(result.get(rSize-2),result.get(rSize-1),points.get(i))<=0) {
				result.remove(result.size()-1);
				rSize--;
			}
			result.add(points.get(i));
		}
		int t=result.size()+1;
		for (int i=points.size()-2;i>=0;i--) {
			int rSize=result.size();
			while ((rSize>=t) && ccw(result.get(rSize-2),result.get(rSize-1),points.get(i))<=0) {
				result.remove(result.size()-1);
				rSize--;
			}
			result.add(points.get(i));
		}
		return result;
	}

	private static boolean sameLine(Point o, Point a, Point b) {
		if (a.compareTo(o)==0 || b.compareTo(o)==0) return true;
		Point oa=new Point(a.x-o.x,a.y-o.y);
		Point ob=new Point(b.x-o.x,b.y-o.y);
		return ccw(o,a,b)==0 && oa.dot(ob)<0;
	}

	private static boolean inside(ArrayList<Point> polygon, Point p) {
		for (int i=0;i<polygon.size()-1;i++) {
			Point prev=polygon.get(i);
			Point next=polygon.get(i+1);
			if (sameLine(p,prev,next)) return true;
		}

		int count=0;
		for (int i=0;i<polygon.size()-1;i++) {
			Point prev=polygon.get(i);
			Point next=polygon.get(i+1);
			if ((prev.y>p.y != next.y>p.y) && (p.x<(next.x-prev.x)*(p.y-prev.y)/(next.y-prev.y)+prev.x)) {
				count++;
			}
		}
		return (count&1)==1;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int P=Integer.parseInt(s);
			ArrayList<Point> points=new ArrayList<>();
			for (int p=0;p<P;p++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			ArrayList<Point> polygon=convexHull(points);

			int R=Integer.parseInt(br.readLine());
			ArrayList<Point> toCheck=new ArrayList<>();
			for (int r=0;r<R;r++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				toCheck.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			StringBuilder sb=new StringBuilder();
			for (int r=0;r<R;r++) sb.append(inside(polygon,toCheck.get(r))?"inside\n":"outside\n");
			System.out.print(sb);
		}

 	}

}
