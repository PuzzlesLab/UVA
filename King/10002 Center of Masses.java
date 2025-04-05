import java.io.BufferedReader;
import java.io.IOException;
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
	
	private static double area(ArrayList<Point> points) {
		double a=0.0;
		for (int i=0;i<points.size()-1;i++) a+=points.get(i).cross(points.get(i+1));
		return 0.5*Math.abs(a);
	}

	private static class Input {
		private BufferedReader br;
		private StringTokenizer st;
		private boolean ended;
		
		public Input() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
		}
		
		private void feedTokenizer() {
			try {
				if (st==null || !st.hasMoreTokens()) {
					String s=br.readLine();
					if (s==null) this.ended=true;
					else this.st=new StringTokenizer(s);
				}
			} catch (IOException exp) {}
		}
		
		public boolean hasNext() {
			feedTokenizer();
			return !this.ended;
		}
		
		public int nextInt() {
			feedTokenizer();
			return Integer.parseInt(st.nextToken());
		}
		
		public double nextDouble() {
			feedTokenizer();
			return Integer.parseInt(st.nextToken());
		}
	}

	public static void main(String [] args) throws Exception {
		Input in=new Input();
		while (in.hasNext()) {
			int M=in.nextInt();
			if (M<3) break;
			
			ArrayList<Point> points=new ArrayList<>();
			for (int m=0;m<M;m++) points.add(new Point(in.nextDouble(),in.nextDouble()));
			points=convexHull(points);
			double area=area(points);

			// Centroid of polygon
			double x=0.0;
			double y=0.0;
			for (int i=0;i<points.size()-1;i++) {
				Point p1=points.get(i);
				Point p2=points.get(i+1);
				x+=p1.cross(p2)*(p1.x+p2.x);
				y+=p1.cross(p2)*(p1.y+p2.y);
			}
			x/=(6*area);
			y/=(6*area);
			System.out.printf("%.3f %.3f\n",x,y);
		}
 	}

}
