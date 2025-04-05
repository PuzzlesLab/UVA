import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(StringTokenizer st) {
			this.x=Double.parseDouble(st.nextToken());
			this.y=Double.parseDouble(st.nextToken());
		}

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double dist(Point p) {
			double dx=this.x-p.x;
			double dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}
		
		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
		
		public String toString() {
			return this.x+","+this.y;
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

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) points.add(new Point(new StringTokenizer(br.readLine())));
			
			ArrayList<Point> sol=convexHull(points);
			Collections.reverse(sol); // Reverse to clockwise.
			double len=0.0;
			for (int i=0;i<sol.size()-1;i++) len+=sol.get(i).dist(sol.get(i+1));
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Region #");
			sb.append(tc++);
			sb.append(":\n");
			for (int i=0;i<sol.size();i++) {
				Point p=sol.get(i);
				sb.append('(');
				sb.append(String.format("%.1f",p.x));
				sb.append(',');
				sb.append(String.format("%.1f",p.y));
				sb.append(")-");
			}
			if (sb.charAt(sb.length()-1)=='-') sb.setLength(sb.length()-1);
			sb.append("\nPerimeter length = ");
			sb.append(String.format("%.2f",len));
			System.out.println(sb);
		}
 	}

}