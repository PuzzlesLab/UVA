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
			return this.x*p.y-p.x*this.y;
		}
	}

	private static interface Shape {
		public boolean inRange(Point p);
	}
	
	private static class Rectangle implements Shape {
		Point p1, p2;
		
		public Rectangle(Point p1, Point p2) {
			this.p1=p1;
			this.p2=p2;
		}
		
		public boolean inRange(Point p) {
			return p.x>this.p1.x && p.x<this.p2.x && p.y>this.p2.y && p.y<this.p1.y;
		}
	}
	
	private static class Circle implements Shape {
		Point center;
		double rad;
		
		public Circle(Point c, double rad) {
			this.center=c;
			this.rad=rad;
		}
		
		public boolean inRange(Point p) {
			double dx=this.center.x-p.x;
			double dy=this.center.y-p.y;
			return dx*dx+dy*dy<rad*rad;
		}
	}

	private static class Triangle implements Shape {
		Point p1, p2, p3;
		
		public Triangle(Point p1, Point p2, Point p3) {
			this.p1=p1;
			this.p2=p2;
			this.p3=p3;
		}
		
		public double area() {
			return 0.5*Math.abs(this.p1.cross(this.p2)+this.p2.cross(this.p3)+this.p3.cross(this.p1));
		}

		public boolean inRange(Point p) {
			double a1=new Triangle(this.p1,this.p2,p).area();
			double a2=new Triangle(this.p2,this.p3,p).area();
			double a3=new Triangle(this.p1,this.p3,p).area();
			if (a1==0 || a2==0 || a3==0) return false;
			double a4=this.area();
			return Math.abs(a1+a2+a3-a4)<=1e-6;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Shape> polygons=new ArrayList<>();
		while (!(s=br.readLine()).equals("*")) {
			StringTokenizer st=new StringTokenizer(s);
			char c=st.nextToken().charAt(0);
			if (c=='t') {
				polygons.add(new Triangle(
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())),
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())),
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()))
				));
			} else if (c=='r') {
				polygons.add(new Rectangle(
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())),
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()))
				));
			} else if (c=='c') {
				polygons.add(new Circle(
						new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())),
						Double.parseDouble(st.nextToken())
				));
			}
		}
		
		int tc=1;
		while (!(s=br.readLine()).equals("9999.9 9999.9")) {
			StringTokenizer st=new StringTokenizer(s);
			Point p=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<polygons.size();i++) {
				if (polygons.get(i).inRange(p)) {
					sb.append("Point ");
					sb.append(tc);
					sb.append(" is contained in figure ");
					sb.append(i+1);
					sb.append('\n');
				}
			}
			if (sb.length()==0) {
				sb.append("Point ");
				sb.append(tc);
				sb.append(" is not contained in any figure\n");
			}
			System.out.print(sb);
			tc++;
		}
 	}

}