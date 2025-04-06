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
		
		public Point rotate(double ang, double dx, double dy) {
			double dx2=dx*Math.cos(ang)-dy*Math.sin(ang);
			double dy2=dx*Math.sin(ang)+dy*Math.cos(ang);
			return new Point(this.x+dx2,this.y+dy2);
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

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			int B=Integer.parseInt(br.readLine());
			
			ArrayList<Point> points=new ArrayList<>();
			double boardArea=0.0;
			for (int b=0;b<B;b++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Point mid=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				double w=Double.parseDouble(st.nextToken());
				double h=Double.parseDouble(st.nextToken());
				double ang=-Math.toRadians(Double.parseDouble(st.nextToken()));
				
				double w2=w*0.5;
				double h2=h*0.5;
				points.add(mid.rotate(ang,-w2,-h2));
				points.add(mid.rotate(ang,-w2,h2));
				points.add(mid.rotate(ang,w2,-h2));
				points.add(mid.rotate(ang,w2,h2));
				boardArea+=w*h;
			}
			points=convexHull(points);
			double allArea=area(points);
			System.out.printf("%.1f %%\n",boardArea/allArea*100);
		}
 	}

}
