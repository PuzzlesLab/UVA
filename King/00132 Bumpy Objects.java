import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static final double EPS=1e-6;

	private static class Point implements Comparable<Point> {
		int id, x, y;

		public Point(int id, int x, int y) {
			this.id=id;
			this.x=x;
			this.y=y;
		}

		public Point(int x, int y) {
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
			if (this.x!=p.x) return this.x-p.x;
			return this.y-p.y;
		}
		
		public String toString() {
			return this.id+"->"+this.x+","+this.y;
		}
	}

	private static boolean ccw(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return vecOA.cross(vecOB)>0;
	}

	private static boolean isAcute(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return Math.acos(vecOA.dot(vecOB)/Math.sqrt(vecOA.norm_sq()*vecOB.norm_sq()))<=Math.PI/2;
	}

	private static boolean online(Point p, Point q, Point r) {
		return Math.abs(p.dist(r)-p.dist(q)-q.dist(r))<EPS;
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

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			StringBuilder sb=new StringBuilder();
			sb.append(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			Point center=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			st=new StringTokenizer(br.readLine());
			ArrayList<Point> points=new ArrayList<>();
			int maxId=1;
			while (st.hasMoreTokens()) {
				Point p=new Point(maxId++,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				if (p.x==0 && p.y==0) break;
				points.add(p);
			}
			
			ArrayList<Point> ch=convexHull(points);
			Collections.reverse(ch);
			int ans=99999;
			for (int i=0;i<ch.size()-1;i++) {
				Point p1=ch.get(i);
				Point p2=ch.get(i+1);
				if (isAcute(center,p1,p2) && isAcute(center,p2,p1)) {
					int baseId=0;
					for (int i2=0;i2<points.size();i2++) if (online(p1,points.get(i2),p2)) {
						baseId=Math.max(baseId,points.get(i2).id);
					}
					if (baseId>0) ans=Math.min(ans,baseId);
				}
			}

			while (sb.length()<20) sb.append(' ');
			sb.append(ans);
			System.out.println(sb);
		}
 	}

}
