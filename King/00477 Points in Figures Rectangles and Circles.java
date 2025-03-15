import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		double x, y;
		
		public Point(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.x=Double.parseDouble(st.nextToken());
			this.y=Double.parseDouble(st.nextToken());
		}
	}
	
	private static interface Shape {
		public boolean in(Point p);
	}

	private static class Circle implements Shape {
		double x, y, r;
		
		public Circle(double x, double y, double r) {
			this.x=x;
			this.y=y;
			this.r=r;
		}

		public boolean in(Point p) {
			double dx=x-p.x;
			double dy=y-p.y;
			return Math.sqrt(dx*dx+dy*dy)<this.r;
		}
	}

	private static class Rectangle implements Shape {
		double minx, miny, maxx, maxy;
		
		public Rectangle(double x1, double y1, double x2, double y2) {
			this.minx=x1;
			this.maxx=x2;
			this.miny=y2;
			this.maxy=y1;
		}
		
		public boolean in(Point p) {
			return p.x>this.minx && p.x<this.maxx && p.y>this.miny && p.y<this.maxy;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Shape> shapes=new ArrayList<>();
		while ((s=br.readLine()).charAt(0)!='*') {
			StringTokenizer st=new StringTokenizer(s);
			if (st.nextToken().charAt(0)=='r') shapes.add(new Rectangle(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			else shapes.add(new Circle(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
		}
		ArrayList<Point> points=new ArrayList<>();
		while (!(s=br.readLine()).equals("9999.9 9999.9")) points.add(new Point(s));
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<points.size();i++) {
			ArrayList<Integer> include=new ArrayList<>();
			for (int n=0;n<shapes.size();n++) if (shapes.get(n).in(points.get(i))) include.add(n+1);

			if (include.isEmpty()) {
				sb.append("Point ");
				sb.append(i+1);
				sb.append(" is not contained in any figure\n");
			} else {
				for (int n=0;n<include.size();n++) {
					sb.append("Point ");
					sb.append(i+1);
					sb.append(" is contained in figure ");
					sb.append(include.get(n));
					sb.append('\n');
				}
			}
		}
		System.out.print(sb);
 	}

}