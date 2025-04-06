import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
		
		public double normSq() {
			return this.x*this.x+this.y*this.y;
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

	private static double angle(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return Math.acos(vecOA.dot(vecOB)/Math.sqrt(vecOA.normSq()*vecOB.normSq()));
	}
	
	private static double rawArea(ArrayList<Point> points) {
		double area=0.0;
		for (int i=0;i<points.size();i++) area+=points.get(i).cross(points.get((i+1)%points.size()));
		return area*0.5;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N<3) break;
			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}

			double min=Math.PI*2;
			double max=0.0;
			boolean pCCW=rawArea(points)<0;
			for (int n=0;n<N+2;n++) {
				Point p1=points.get(n%N);
				Point p2=points.get((n+1)%N);
				Point p3=points.get((n+2)%N);
				double ang=angle(p1,p2,p3);
				boolean cCCW=ccw(p2,p1,p3);
				if (cCCW!=pCCW) ang=Math.PI*2-ang;
				min=Math.min(min,ang);
				max=Math.max(max,ang);
			}
			System.out.printf("%.6f %.6f\n",Math.toDegrees(min),Math.toDegrees(max));
		}
 	}

}
