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
		
		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}

		public double dot(Point p) {
			return this.x*p.x+this.y*p.y;
		}

		public double normSq() {
			return this.x*this.x+this.y*this.y;
		}

		public double dist(Point p) {
			double dx=p.x-this.x;
			double dy=p.y-this.y;
			return Math.sqrt(dx*dx+dy*dy);
		}
	}

	private static double angle(Point a, Point o, Point b) {
		Point vecOA=new Point(a.x-o.x,a.y-o.y);
		Point vecOB=new Point(b.x-o.x,b.y-o.y);
		return Math.acos(vecOA.dot(vecOB)/Math.sqrt(vecOA.normSq()*vecOB.normSq()));
	}

	private static double area(ArrayList<Point> points) {
		double a=0.0;
		for (int i=0;i<points.size();i++) a+=points.get(i).cross(points.get((i+1)%points.size()));
		return 0.5*Math.abs(a);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double d=Double.parseDouble(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			if (d==0 && N==0) break;

			ArrayList<Point> points=new ArrayList<>();
			for (int n=0;n<N;n++) {
				points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}
			double area=area(points);
			
			double badArea=0.0;
			for (int i=0;i<points.size();i++) {
				Point p1=points.get((i-1+points.size())%points.size());
				Point p2=points.get(i);
				Point p3=points.get((i+1)%points.size());
				double ang=angle(p1,p2,p3)*0.5;
				double side=d/Math.tan(ang);
				badArea+=(p2.dist(p3)-side)*d;
			}
			System.out.printf("%.3f\n",area-badArea);
		}
 	}

}