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
	
	private static class Rectangle {
		double minx, miny, maxx, maxy;
		
		public Rectangle(String s) {
			StringTokenizer st=new StringTokenizer(s);
			st.nextToken(); // Useless
			double x1=Double.parseDouble(st.nextToken());
			double y1=Double.parseDouble(st.nextToken());
			double x2=Double.parseDouble(st.nextToken());
			double y2=Double.parseDouble(st.nextToken());
			
			this.minx=Math.min(x1,x2);
			this.maxx=Math.max(x1,x2);
			this.miny=Math.min(y1,y2);
			this.maxy=Math.max(y1,y2);
		}
		
		public boolean in(Point p) {
			return p.x>this.minx && p.x<this.maxx && p.y>this.miny && p.y<this.maxy;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Rectangle> rectangles=new ArrayList<>();
		while ((s=br.readLine()).charAt(0)!='*') rectangles.add(new Rectangle(s));
		ArrayList<Point> points=new ArrayList<>();
		while (!(s=br.readLine()).equals("9999.9 9999.9")) points.add(new Point(s));
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<points.size();i++) {
			ArrayList<Integer> include=new ArrayList<>();
			for (int n=0;n<rectangles.size();n++) if (rectangles.get(n).in(points.get(i))) include.add(n+1);
			

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
