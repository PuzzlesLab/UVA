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
		
		public int compareTo(Point p) {
			if (this.y!=p.y) return Double.compare(this.y,p.y);
			return Double.compare(this.x,p.x);
		}
	}

	private static boolean ccw(Point o, Point a, Point b) {
		Point vOA=new Point(a.x-o.x,a.y-o.y);
		Point vOB=new Point(b.x-o.x,b.y-o.y);
		return vOA.cross(vOB)>0;
	}

	private static ArrayList<Point> convexHull(ArrayList<Point> points) {
		Collections.sort(points);
		ArrayList<Point> result=new ArrayList<>();
		
		for (int i=0;i<points.size();i++) {
			Point p=points.get(i);
			while (result.size()>=2 && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) {
				result.remove(result.size()-1);
			}
			result.add(p);
		}

		int base=result.size()+1;
		for (int i=points.size()-1;i>=0;i--) {
			Point p=points.get(i);
			while (result.size()>=base && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) {
				result.remove(result.size()-1);
			}
			result.add(p);
		}
		return result;
	}

	private static double area(ArrayList<Point> points, boolean cycle) {
		double a=0.0;
		for (int i=0;i<points.size()-1;i++) a+=points.get(i).cross(points.get(i+1));
		if (cycle) a+=points.get(points.size()-1).cross(points.get(0));
		return 0.5*Math.abs(a);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}
			
			double a1=area(points,true);
			points=convexHull(points);
			double a2=area(points,false);

			StringBuilder sb=new StringBuilder();
			sb.append("Tile #");
			sb.append(tc++);
			sb.append("\nWasted Space = ");
			sb.append(String.format("%.2f", 100*(a2-a1)/a2));
			sb.append(" %\n");
			System.out.println(sb);
		}
 	}

}